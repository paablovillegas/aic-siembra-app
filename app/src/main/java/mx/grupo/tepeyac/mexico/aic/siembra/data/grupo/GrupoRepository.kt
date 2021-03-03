package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

class GrupoRepository {

    fun getGrupos(): List<GrupoItem> {
        val data =
            "{\"ok\":true,\"lugares\":[{\"tipo_lugar\":\"cuadrilla\",\"trabajadores\":[{\"_id\":\"603d3300f8a6fb38386212e4\",\"nombres\":\"Luis Enrique\",\"apellido_paterno\":\"Cornejo\",\"fecha_alta\":\"1970-01-19T16:30:15.191Z\",\"salario\":{\"sueldo\":220},\"__v\":0},{\"_id\":\"603d473e0e3bd61e2cdb23d4\",\"nombres\":\"Danae\",\"apellido_paterno\":\"Villanueva\",\"fecha_alta\":\"1970-01-19T16:30:15.191Z\",\"salario\":{\"sueldo\":220},\"__v\":0}],\"_id\":\"603d07f42da8a318f86dfc74\",\"fletes\":{\"regular\":2200},\"lugar\":\"Juan Mata\",\"__v\":0}]}"
        val gson: Gson = GsonBuilder().create()
        val ranchoResponse = gson.fromJson(data, ResponseGrupoList::class.java)
        return ranchoResponse.grupos
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
                    trabajadores = compareTrabajadores(interno.trabajadores, externo.trabajadores)
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
                    trabajadores = grupo.trabajadores.map { table -> table.copy(delete = true) }
                )
            }
        return listOf(inserts, updates, deletes)
    }

    private fun compareTrabajadores(internos: List<Trabajador>, externos: List<Trabajador>): List<Trabajador> {
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


}