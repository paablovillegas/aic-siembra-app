package mx.grupo.tepeyac.mexico.aic.siembra.data.area

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import mx.grupo.tepeyac.mexico.aic.siembra.data.AppDatabase
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.Actividad
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.ActividadDao
import mx.grupo.tepeyac.mexico.aic.siembra.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AreaRepository(context: Context) {
    private val areaApi: AreaApi = ServiceGenerator.createService(context, AreaApi::class.java, "a")
    private val areaDao: AreaDao = AppDatabase.getInstance(context).areaDao
    private val actividadDao: ActividadDao = AppDatabase.getInstance(context).actividadDao

    fun insert(area: Area) = areaDao.insert(area)
    fun insert(actividad: Actividad) = actividadDao.insert(actividad)

    fun getActividadID(id: String): Long? = actividadDao.getActividadID(id)
    fun getActividadID(id: Long): String? = actividadDao.getActividadID(id)
    fun getActividad(id: Long): Actividad = actividadDao.getActividad(id)
    fun getAreasLD(): LiveData<List<AreaWithActividades>> = areaDao.getAreasLD()
    fun getAreaLD(id: Long): LiveData<AreaWithActividades> = areaDao.getAreaLD(id)
    fun getArea(id: Long): Area = areaDao.getArea(id)

    fun insert(awa: AreaWithActividades) {
        val id = areaDao.insert(awa.area)
        actividadDao.insert(awa.actividades.map { it.copy(idArea = id) })
    }

    fun update(awa: AreaWithActividades) {
        areaDao.update(awa.area)
        actividadDao.update(awa.actividades)
    }

    fun getAreas(): List<AreaItem> {
        val data =
            "{\"ok\":true,\"areas\":[{\"_id\":\"60394fc5d475741958ac62ce\",\"area\":\"Generales\",\"actividades\":[{\"_id\":\"6039535e6896ab41dc527dc8\",\"actividad\":\"Ingeniero de campo\"}],\"__v\":0}]}"
        val gson: Gson = GsonBuilder().create()
        val areaResponse = gson.fromJson(data, ResponseAreaList::class.java)
        return areaResponse.areas
    }

    fun updateDatabase(awa: List<List<AreaWithActividades>>) {
        if (awa.size != 3)
            return
        awa[0].forEach { area ->
            val id = areaDao.insert(area.area)
            updateDatabaseActividades(area.actividades
                .map { actividad -> actividad.copy(idArea = id) }
            )
        }
        awa[1].forEach { area ->
            areaDao.update(area.area)
            updateDatabaseActividades(area.actividades)
        }
        awa[2].forEach { area ->
            areaDao.delete(area.area)
            updateDatabaseActividades(area.actividades)
        }
    }

    private fun updateDatabaseActividades(actividades: List<Actividad>) {
        actividades.forEach { actividad ->
            when {
                actividad.delete -> actividadDao.delete(actividad)
                actividad.id > 0 -> actividadDao.update(actividad)
                else -> actividadDao.insert(actividad)
            }
        }
    }

    fun compareAreas(
        internos: List<AreaWithActividades>,
        externos: List<AreaWithActividades>,
    ): List<List<AreaWithActividades>> {
        val inserts = externos.filter { externo ->
            internos.find { interno ->
                interno.area.idArea == externo.area.idArea
            } == null
        }
        val updates: List<AreaWithActividades> = externos.mapNotNull { externo ->
            val interno: AreaWithActividades? = internos.find { i ->
                i.area.idArea == externo.area.idArea
            }
            if (interno != null)
                return@mapNotNull AreaWithActividades(
                    area = externo.area.copy(id = interno.area.id),
                    actividades = compareActividades(
                        interno.actividades,
                        externo.actividades.map { it.copy(idArea = interno.area.id) }
                    )
                )
            return@mapNotNull null
        }
        val deletes = internos
            .filter { interno ->
                externos.find { externo ->
                    externo.area.idArea == interno.area.idArea
                } == null
            }
            .map { area ->
                area.copy(actividades = area.actividades.map { act -> act.copy(delete = true) })
            }
        return listOf(inserts, updates, deletes)
    }

    private fun compareActividades(
        internos: List<Actividad>,
        externos: List<Actividad>
    ): List<Actividad> {
        val inserts = externos.filter { externo ->
            internos.find { interno ->
                interno.idActividad == externo.idActividad
            } == null
        }
        val updates = externos.mapNotNull { externo ->
            val interno = internos.find { interno ->
                interno.idActividad == externo.idActividad && !interno.editado
            }
            if (interno != null) externo.copy(id = interno.id)
            else null
        }
        val deletes = internos.filter { interno ->
            externos.find { externo ->
                interno.idActividad == externo.idActividad
            } == null
        }.map { i -> i.copy(delete = true) }
        return listOf(inserts, updates, deletes).flatten()
    }

    fun downloadAreas() {
        areaApi.getAreas().enqueue(object : Callback<ResponseAreaList> {
            override fun onResponse(
                call: Call<ResponseAreaList>,
                response: Response<ResponseAreaList>
            ) {
                response.body()?.let { a ->
                    val areas = a.areas
                    val areasWitActividades: List<AreaWithActividades> =
                        areas.map { it.getAreaWithActividades() }
                    val data = compareAreas(areaDao.getAreas(), areasWitActividades)
                    updateDatabase(data)
                }
            }

            override fun onFailure(call: Call<ResponseAreaList>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun insertArea(area: AreaWithActividades) {
        area.toSendAreaItem().let { sai ->
            areaApi.insertArea(sai).enqueue(object : Callback<ResponseAreaItem> {
                override fun onResponse(
                    call: Call<ResponseAreaItem>,
                    response: Response<ResponseAreaItem>
                ) {
                    response.body()?.let { areaItem ->
                        val nuevo = area.actividades
                            .zip(areaItem.area.toActividadesEntities()) { i, e ->
                                i.copy(idActividad = e.idActividad)
                            }
                        update(
                            AreaWithActividades(
                                area = areaItem.area.toEntity()
                                    .copy(id = area.area.id),
                                actividades = nuevo
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<ResponseAreaItem>, t: Throwable) {
                    Log.e("TAG", "onFailure: ${t.stackTraceToString()}")
                }
            })
        }
    }

    fun updateArea(area: AreaWithActividades) {
        area.area.idArea?.let {
            area.toSendAreaItem().let { sai ->
                areaApi.updateArea(it, sai).enqueue(object : Callback<ResponseAreaItem> {
                    override fun onResponse(
                        call: Call<ResponseAreaItem>,
                        response: Response<ResponseAreaItem>
                    ) {
                        response.body()?.let {
                            val rwt = it.area.getAreaWithActividades()
                            update(
                                AreaWithActividades(
                                    rwt.area.copy(id = area.area.id),
                                    rwt.actividades.zip(area.actividades) { e, i ->
                                        e.copy(id = i.id, idArea = i.idArea)
                                    }
                                )
                            )
                        }
                    }

                    override fun onFailure(call: Call<ResponseAreaItem>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            }
        }
    }
}