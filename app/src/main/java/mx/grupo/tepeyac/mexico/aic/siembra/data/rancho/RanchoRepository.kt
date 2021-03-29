package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
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

    fun insert(rancho: Rancho) = ranchoDao.insert(rancho)
    fun update(rancho: Rancho) = ranchoDao.update(rancho)
    fun insert(tabla: Tabla) = tablaDao.insert(tabla)
    fun update(tabla: Tabla) = tablaDao.update(tabla)

    fun insert(rwt: RanchoWithTablas) {
        val id = ranchoDao.insert(rwt.rancho)
        tablaDao.insert(rwt.tablas.map { it.copy(idRancho = id) })
    }

    fun update(rwt: RanchoWithTablas) {
        ranchoDao.update(rwt.rancho)
        tablaDao.update(rwt.tablas)
    }

    fun getRanchos(): List<RanchoWithTablas> = ranchoDao.getRanchos()
    fun getRanchosNoSubidos(): List<RanchoWithTablas> = ranchoDao.getRanchosNoSubidos()
    fun getRanchosLD(): LiveData<List<RanchoWithTablas>> = ranchoDao.getRanchosLD()
    fun getRanchoLD(idRancho: Long): LiveData<RanchoWithTablas> = ranchoDao.getRanchoLD(idRancho)
    fun getRancho(idRancho: Long): Rancho = ranchoDao.getRancho(idRancho)
    fun getRanchoID(id: String): Long? = ranchoDao.getRanchoID(id)
    fun getRanchoID(id: Long): String? = ranchoDao.getRanchoID(id)
    fun getTablaID(id: String): Long? = tablaDao.getTablaID(id)
    fun getTablaID(id: Long): String? = tablaDao.getTablaID(id)
    fun getTabla(idTabla: Long): Tabla = tablaDao.getTabla(idTabla)
    fun getTablasLD(idRancho: Long): LiveData<List<Tabla>> = tablaDao.getTablasLD(idRancho)
    fun getTablas(idRancho: Long): List<Tabla> = tablaDao.getTablas(idRancho)

    fun syncRanchos() {
        getRanchosNoSubidos().forEach { insertRancho(it) }
    }

    fun updateDatabaseRanchos(rwt: List<List<RanchoWithTablas>>) {
        if (rwt.size != 3)
            return
        rwt[0].forEach { rancho ->
            val id = ranchoDao.insert(rancho.rancho)
            updateDatabaseTablas(rancho.tablas
                .map { tabla -> tabla.copy(idRancho = id) }
            )
        }
        rwt[1].forEach { rancho ->
            ranchoDao.update(rancho.rancho)
            updateDatabaseTablas(rancho.tablas)
        }
        rwt[2].forEach { rancho ->
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
                    tablas = compareTablas(
                        interno.tablas,
                        externo.tablas.map { it.copy(idRancho = interno.rancho.id) }
                    )
                )
            return@mapNotNull null
        }
        val deletes = internos
            .filter { interno ->
                externos.find { externo ->
                    externo.rancho.idRancho == interno.rancho.idRancho
                } == null
            }
            .map { rancho ->
                rancho.copy(tablas = rancho.tablas.map { table -> table.copy(delete = true) })
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
            if (interno != null)
                externo.copy(
                    id = interno.id,
                    idRancho = interno.idRancho,
                )
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
                    val ranchos = r.ranchos
                    val ranchosWithTablas: List<RanchoWithTablas> =
                        ranchos.map { it.getRanchoWithTablas() }
                    val data = compareRanchos(ranchoDao.getRanchos(), ranchosWithTablas)
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

    private fun insertRancho(rancho: RanchoWithTablas) {
        rancho.toSendRanchoItem().let { sri ->
            ranchoApi.insertRancho(sri).enqueue(object : Callback<ResponseRanchoItem> {
                override fun onResponse(
                    call: Call<ResponseRanchoItem>,
                    response: Response<ResponseRanchoItem>
                ) {
                    response.body()?.let { ranchoItem ->
                        val nuevo = rancho.tablas
                            .zip(ranchoItem.rancho.getTablasEntities()) { i, e ->
                                i.copy(idTabla = e.idTabla)
                            }
                        update(
                            RanchoWithTablas(
                                rancho = ranchoItem.rancho.toEntity()
                                    .copy(id = rancho.rancho.id),
                                tablas = nuevo
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<ResponseRanchoItem>, t: Throwable) {
                    Log.e("TAG", "onFailure: ${t.stackTraceToString()}")
                }
            })
        }
    }

    fun updateRancho(rancho: RanchoWithTablas) {
        rancho.rancho.idRancho?.let {
            rancho.toSendRanchoItem().let { sri ->
                ranchoApi.updateRancho(it, sri).enqueue(object : Callback<ResponseRanchoItem> {
                    override fun onResponse(
                        call: Call<ResponseRanchoItem>,
                        response: Response<ResponseRanchoItem>
                    ) {
                        response.body()?.let {
                            val rwt = it.rancho.getRanchoWithTablas()
                            update(
                                RanchoWithTablas(
                                    rwt.rancho.copy(id = rancho.rancho.id),
                                    rwt.tablas.zip(rancho.tablas) { e, i ->
                                        e.copy(id = i.id, idRancho = i.idRancho)
                                    }
                                )
                            )
                        }
                    }

                    override fun onFailure(call: Call<ResponseRanchoItem>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            }
        }
    }
}