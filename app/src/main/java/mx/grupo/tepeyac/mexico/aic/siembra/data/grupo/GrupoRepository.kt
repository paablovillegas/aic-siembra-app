package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.AppDatabase
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.AsistenciaGrupoWithAsistencias
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.TrabajadorDao
import mx.grupo.tepeyac.mexico.aic.siembra.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class GrupoRepository(context: Context) {
    private val grupoApi: GrupoApi =
        ServiceGenerator.createService(context, GrupoApi::class.java, "a")
    private val grupoDao: GrupoDao = AppDatabase.getInstance(context).grupoDao
    private val trabajadorDao: TrabajadorDao = AppDatabase.getInstance(context).trabajadorDao

    fun insert(grupo: Grupo) = grupoDao.insert(grupo)
    fun insert(trabajador: Trabajador) = trabajadorDao.insert(trabajador)

    fun getTrabajadorID(id: String): Long? = trabajadorDao.getTrabajadorID(id)
    fun getTrabajadorID(id: Long): String? = trabajadorDao.getTrabajadorID(id)
    fun getTrabajador(id: Long): Trabajador = trabajadorDao.getTrabajador(id)
    fun getGruposLD(): LiveData<List<GrupoWithTrabajadores>> = grupoDao.getGruposLD()
    fun getGrupoLD(id: Long): LiveData<GrupoWithTrabajadores> = grupoDao.getGrupoLD(id)
    fun getGrupo(id: Long): Grupo = grupoDao.getGrupo(id)

    fun geGruposDisponibles(start: Date, end: Date) =
        grupoDao.getGruposDisponibles(start, end)

    fun getGrupoID(id: Long): String? = grupoDao.getGrupoID(id)
    fun getGrupoID(id: String): Long? = grupoDao.getGrupoID(id)
    fun insert(rwt: GrupoWithTrabajadores) {
        val id = grupoDao.insert(rwt.grupo)
        trabajadorDao.insert(rwt.trabajadores.map { it.copy(idGrupo = id) })
    }

    fun update(rwt: GrupoWithTrabajadores) {
        grupoDao.update(rwt.grupo)
        trabajadorDao.update(rwt.trabajadores)
    }

    fun getGruposNoSubidos(): List<GrupoWithTrabajadores> = grupoDao.getGruposNoSubidos()

    fun syncGrupos() {
        getGruposNoSubidos().forEach { insertGrupo(it) }
    }

    fun updateDatabaseGrupos(rwt: List<List<GrupoWithTrabajadores>>) {
        if (rwt.size != 3)
            return
        rwt[0].forEach { grupo ->
            val id = grupoDao.insert(grupo.grupo)
            updateDatabaseTrabajadores(grupo.trabajadores
                .map { trabajador -> trabajador.copy(idGrupo = id) }
            )
        }
        rwt[1].forEach { grupo ->
            grupoDao.update(grupo.grupo)
            updateDatabaseTrabajadores(grupo.trabajadores)
        }
        rwt[2].forEach { grupo ->
            grupoDao.delete(grupo.grupo)
            updateDatabaseTrabajadores(grupo.trabajadores)
        }
    }

    private fun updateDatabaseTrabajadores(trabajadores: List<Trabajador>) {
        trabajadores.forEach { trabajador ->
            when {
                trabajador.delete -> trabajadorDao.delete(trabajador)
                trabajador.id > 0 -> trabajadorDao.update(trabajador)
                else -> trabajadorDao.insert(trabajador)
            }
        }
    }

    fun compareGrupos(
        internos: List<GrupoWithTrabajadores>,
        externos: List<GrupoWithTrabajadores>
    ): List<List<GrupoWithTrabajadores>> {
        val inserts = externos.filter { externo ->
            internos.find { interno ->
                interno.grupo.idGrupo == externo.grupo.idGrupo
            } == null
        }
        val updates: List<GrupoWithTrabajadores> = externos.mapNotNull { externo ->
            val interno: GrupoWithTrabajadores? = internos.find { i ->
                i.grupo.idGrupo == externo.grupo.idGrupo
            }
            if (interno != null)
                return@mapNotNull GrupoWithTrabajadores(
                    grupo = externo.grupo.copy(id = interno.grupo.id),
                    trabajadores = compareTrabajadores(
                        interno.trabajadores,
                        externo.trabajadores.map { it.copy(idGrupo = interno.grupo.id) }
                    )
                )
            return@mapNotNull null
        }
        val deletes = internos
            .filter { interno ->
                externos.find { externo ->
                    externo.grupo.idGrupo == interno.grupo.idGrupo
                } == null
            }
            .map { grupo ->
                grupo.copy(
                    trabajadores = grupo.trabajadores.map { t -> t.copy(delete = true) }
                )
            }
        return listOf(inserts, updates, deletes)
    }

    private fun compareTrabajadores(
        internos: List<Trabajador>,
        externos: List<Trabajador>
    ): List<Trabajador> {
        val inserts = externos.filter { externo ->
            internos.find { interno ->
                interno.idTrabajador == externo.idTrabajador
            } == null
        }
        val updates = externos.mapNotNull { externo ->
            val interno = internos.find { interno ->
                interno.idTrabajador == externo.idTrabajador && !interno.editado
            }
            if (interno != null) externo.copy(id = interno.id)
            else null
        }
        val deletes = internos.filter { interno ->
            externos.find { externo ->
                interno.idTrabajador == externo.idTrabajador
            } == null
        }.map { i -> i.copy(delete = true) }
        return listOf(inserts, updates, deletes).flatten()
    }

    fun downloadGrupos() {
        grupoApi.getGrupos().enqueue(object : Callback<ResponseGrupoList> {
            override fun onResponse(
                call: Call<ResponseGrupoList>,
                response: Response<ResponseGrupoList>
            ) {
                response.body()?.let { g ->
                    val grupos = g.grupos
                    val gruposWithTrabajadores: List<GrupoWithTrabajadores> =
                        grupos.map { it.getGrupoWithTrabajadores() }
                    val data = compareGrupos(grupoDao.getGrupos(), gruposWithTrabajadores)
                    updateDatabaseGrupos(data)
                }
            }

            override fun onFailure(call: Call<ResponseGrupoList>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.stackTraceToString()}")
            }
        })
    }

    fun insertGrupo(gwt: GrupoWithTrabajadores) {
        gwt.toSendGrupoItem().let { sgi ->
            grupoApi.insertGrupo(sgi).enqueue(object : Callback<ResponseGrupoItem> {
                override fun onResponse(
                    call: Call<ResponseGrupoItem>,
                    response: Response<ResponseGrupoItem>
                ) {
                    response.body()?.let { grupoItem ->
                        val nuevo = gwt.trabajadores
                            .zip(grupoItem.grupo.toTrabajadoresEntities()) { i, e ->
                                i.copy(idGrupo = e.idGrupo)
                            }
                        update(
                            GrupoWithTrabajadores(
                                grupo = grupoItem.grupo.toEntity()
                                    .copy(id = gwt.grupo.id),
                                trabajadores = nuevo
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<ResponseGrupoItem>, t: Throwable) {
                    t.printStackTrace()
                }
            })

        }
    }

    fun updateGrupo(gwt: GrupoWithTrabajadores) {
        gwt.grupo.idGrupo?.let {
            gwt.toSendGrupoItem().let { sri ->
                grupoApi.updateGrupo(it, sri).enqueue(object : Callback<ResponseGrupoItem> {
                    override fun onResponse(
                        call: Call<ResponseGrupoItem>,
                        response: Response<ResponseGrupoItem>
                    ) {
                        response.body()?.let {
                            val rwt = it.grupo.getGrupoWithTrabajadores()
                            update(
                                GrupoWithTrabajadores(
                                    rwt.grupo.copy(id = gwt.grupo.id),
                                    rwt.trabajadores.zip(gwt.trabajadores) { e, i ->
                                        e.copy(id = i.id, idGrupo = i.idGrupo)
                                    }
                                )
                            )
                        }
                    }

                    override fun onFailure(call: Call<ResponseGrupoItem>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            }
        }
    }
}