package mx.grupo.tepeyac.mexico.aic.siembra.data.area

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.Actividad

class AreaRepository {

    fun getAreas(): List<AreaItem> {
        val data =
            "{\"ok\":true,\"areas\":[{\"_id\":\"60394fc5d475741958ac62ce\",\"area\":\"Generales\",\"actividades\":[{\"_id\":\"6039535e6896ab41dc527dc8\",\"actividad\":\"Ingeniero de campo\"}],\"__v\":0}]}"
        val gson: Gson = GsonBuilder().create()
        val ranchoResponse = gson.fromJson(data, ResponseAreaList::class.java)
        return ranchoResponse.areas
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
                    actividades = compareActividades(interno.actividades, externo.actividades)
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
                area.copy(
                    actividades = area.actividades.map { act -> act.copy(delete = true) }
                )
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
}