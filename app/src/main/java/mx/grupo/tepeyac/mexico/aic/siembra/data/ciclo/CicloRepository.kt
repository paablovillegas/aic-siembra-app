package mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class CicloRepository {

    fun getCiclos(): List<CicloItem> {
        val data =
            "{\"ok\":true,\"ciclos\":[{\"_id\":\"60397ee0b8acb743a4d2e29e\",\"ciclo\":\"Ciclo 1\",\"tabla\":\"6039390d8f00b52f08780408\",\"producto\":{\"_id\":\"60397652f5e0a43f2018f93e\",\"producto\":\"Brócoli\",\"__v\":0},\"fechas\":{\"inicio\":\"2020-01-19T16:26:20.690Z\"},\"__v\":0},{\"_id\":\"60397f3f99670c165063ad2f\",\"ciclo\":\"Ciclo 2\",\"tabla\":\"6039390d8f00b52f08780408\",\"producto\":{\"_id\":\"60397652f5e0a43f2018f93e\",\"producto\":\"Brócoli\",\"__v\":0},\"fechas\":{\"inicio\":\"2021-01-19T16:26:20.690Z\"},\"__v\":0}]}"
        val gson: Gson = GsonBuilder().create()
        val cicloResponse = gson.fromJson(data, ResponseCicloList::class.java)
        return cicloResponse.ciclos
    }

    fun compareProductos(
        internos: List<Ciclo>,
        externos: List<Ciclo>
    ): List<Ciclo> {
        val inserts = externos.filter { externo ->
            internos.find { interno ->
                interno.idCiclo == externo.idCiclo
            } == null
        }
        val updates = externos.mapNotNull { externo ->
            val interno = internos.find { interno ->
                interno.idCiclo == externo.idCiclo && !interno.editado
            }
            if (interno != null) externo.copy(id = interno.id)
            else null
        }
        val deletes = internos.filter { interno ->
            externos.find { externo ->
                interno.idProducto == externo.idProducto
            } == null
        }.map { i -> i.copy(delete = true) }
        return listOf(inserts, updates, deletes).flatten()
    }
}