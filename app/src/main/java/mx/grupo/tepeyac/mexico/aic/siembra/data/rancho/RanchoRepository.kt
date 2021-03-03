package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla

class RanchoRepository {

    fun getRanchos(): List<RanchoItem> {
        val data =
            "{\"ok\":true,\"ranchos\":[{\"_id\":\"603935b0afbf59217c1afc1d\",\"rancho\":\"Santa Cruz\",\"alias\":\"SC\",\"tablas\":[{\"_id\":\"6039390d8f00b52f08780408\",\"tabla\":\"Tabla 1\",\"libre\":true},{\"_id\":\"6039483445bc750a5ce98873\",\"tabla\":\"Tabla 3\",\"libre\":true},{\"_id\":\"6039450b0b62433674e6c5c9\",\"tabla\":\"Tabla 10\",\"libre\":true}],\"__v\":6}]}"
        val gson: Gson = GsonBuilder().create()
        val ranchoResponse = gson.fromJson(data, ResponseRanchoList::class.java)
        return ranchoResponse.ranchos
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
            val interno: RanchoWithTablas? = internos
                .find { i -> i.rancho.idRancho == externo.rancho.idRancho }
            if (interno != null)
                return@mapNotNull RanchoWithTablas(
                    rancho = externo.rancho.copy(id = interno.rancho.id),
                    tablas = compareTablas(interno.tablas, externo.tablas)
                )
            return@mapNotNull null
        }
        val deletes = internos.filter { interno ->
            externos.find { externo ->
                externo.rancho.idRancho == interno.rancho.idRancho
            } == null
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
}