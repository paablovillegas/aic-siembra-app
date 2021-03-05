package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import mx.grupo.tepeyac.mexico.aic.siembra.data.AppDatabase
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.TablaDao
import mx.grupo.tepeyac.mexico.aic.siembra.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RanchoRepository(context: Context) {
    private val ranchoDao: RanchoDao = AppDatabase.getInstance(context).ranchoDao
    private val tablaDao: TablaDao = AppDatabase.getInstance(context).tablaDao
    private val ranchoApi: RanchoApi = ServiceGenerator
        .createService(context, RanchoApi::class.java, "1")

    fun getRanchos(): List<RanchoItem> {
        val data =
            "{\"ok\":true,\"ranchos\":[{\"_id\":\"603935b0afbf59217c1afc1d\",\"rancho\":\"Santa Cruz\",\"alias\":\"SC\",\"tablas\":[{\"_id\":\"6039390d8f00b52f08780408\",\"tabla\":\"Tabla 1\",\"libre\":true},{\"_id\":\"6039483445bc750a5ce98873\",\"tabla\":\"Tabla 3\",\"libre\":true},{\"_id\":\"6039450b0b62433674e6c5c9\",\"tabla\":\"Tabla 10\",\"libre\":true}],\"__v\":6}]}"
        val gson: Gson = GsonBuilder().create()
        val ranchoResponse = gson.fromJson(data, ResponseRanchoList::class.java)
        return ranchoResponse.ranchos
    }

    fun getRanchos2() = ranchoDao.getRanchos()

    fun updateDatabaseRanchos(ranchosWithTablas: List<List<RanchoWithTablas>>) {
        if (ranchosWithTablas.size != 3)
            return
        ranchosWithTablas[0].forEach { rancho ->
            ranchoDao.insert(rancho.rancho)
            updateDatabaseTablas(rancho.tablas)
        }
        ranchosWithTablas[1].forEach { rancho ->
            ranchoDao.update(rancho.rancho)
            updateDatabaseTablas(rancho.tablas)
        }
        ranchosWithTablas[0].forEach { rancho ->
            ranchoDao.delete(rancho.rancho)
            updateDatabaseTablas(rancho.tablas)
        }
    }

    private fun updateDatabaseTablas(tablas: List<Tabla>) {
        tablas.forEach { tabla ->
            when {
                tabla.delete -> tablaDao.delete(tabla)
                tabla.id > 0 -> tablaDao.update(tabla)
                else -> tablaDao.insert(tabla)
            }
        }
    }

    fun compareRanchos(
        internos: List<RanchoWithTablas>,
        externos: List<RanchoWithTablas>
    ): List<List<RanchoWithTablas>> {
        val inserts = externos.filter { externo ->
            internos.find { interno ->
                interno.rancho.idRancho == externo.rancho.idRancho
            } == null
        }
        val updates: List<RanchoWithTablas> = externos.mapNotNull { externo ->
            val interno: RanchoWithTablas? = internos.find { i ->
                i.rancho.idRancho == externo.rancho.idRancho
            }
            if (interno != null)
                return@mapNotNull RanchoWithTablas(
                    rancho = externo.rancho.copy(id = interno.rancho.id),
                    tablas = compareTablas(interno.tablas, externo.tablas)
                )
            return@mapNotNull null
        }
        val deletes = internos
            .filter { interno ->
                externos.find { externo ->
                    externo.rancho.idRancho == interno.rancho.idRancho
                } == null
            }
            .map { tabla ->
                tabla.copy(
                    tablas = tabla.tablas.map { table -> table.copy(delete = true) }
                )
            }
        return listOf(inserts, updates, deletes)
    }

    private fun compareTablas(internos: List<Tabla>, externos: List<Tabla>): List<Tabla> {
        val inserts = externos.filter { externo ->
            internos.find { interno ->
                interno.idTabla == externo.idTabla
            } == null
        }
        val updates = externos.mapNotNull { externo ->
            val interno = internos.find { interno ->
                interno.idTabla == externo.idTabla && !interno.editado
            }
            if (interno != null) externo.copy(id = interno.id)
            else null
        }
        val deletes = internos.filter { interno ->
            externos.find { externo ->
                interno.idTabla == externo.idTabla
            } == null
        }.map { i -> i.copy(delete = true) }
        return listOf(inserts, updates, deletes).flatten()
    }

    fun downloadRanchos() {
        ranchoApi.getRanchos().enqueue(object : Callback<ResponseRanchoList> {
            override fun onResponse(
                call: Call<ResponseRanchoList>,
                response: Response<ResponseRanchoList>
            ) {
                response.body()?.let { r ->
                    val responseRanchoList = r.ranchos
                    val ranchosWithTablas = responseRanchoList
                        .map { it.getRanchoWithTablas() }
                    val interno = ranchoDao.getRanchos()
                    val data = compareRanchos(interno, ranchosWithTablas)
                    Log.i(
                        "TAG",
                        "onResponse: I:${data[0].size} U:${data[1].size} D:${data[2].size}"
                    )
                    updateDatabaseRanchos(data)
                }
            }

            override fun onFailure(call: Call<ResponseRanchoList>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun insertRancho(rancho: RanchoItem) {
        ranchoApi.insertRancho(rancho).enqueue(object : Callback<ResponseRanchoItem> {
            override fun onResponse(
                call: Call<ResponseRanchoItem>,
                response: Response<ResponseRanchoItem>
            ) {
                response.body()?.let {
                    //ranchoDao.insert(it.rancho.toEntity().copy(id = ))
                }
            }

            override fun onFailure(call: Call<ResponseRanchoItem>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun updateRancho(rancho: RanchoWithTablas) {
        rancho.rancho.idRancho?.let {
            ranchoApi.updateRancho(it, rancho.toResponseItem())
                .enqueue(object : Callback<ResponseRanchoItem> {
                    override fun onResponse(
                        call: Call<ResponseRanchoItem>,
                        response: Response<ResponseRanchoItem>
                    ) {
                        response.body()?.let { res ->
                            Log.i("TAG", "onResponse: $res")
                        }
                    }

                    override fun onFailure(call: Call<ResponseRanchoItem>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
        }
    }
}