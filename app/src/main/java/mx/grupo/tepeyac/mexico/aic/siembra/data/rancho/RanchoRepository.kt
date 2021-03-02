package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import mx.grupo.tepeyac.mexico.aic.siembra.data.ResponseRanchoList

class RanchoRepository {

    fun getRanchos(): List<Rancho> {
        val data =
            "{\"ok\":true,\"ranchos\":[{\"_id\":\"603935b0afbf59217c1afc1d\",\"rancho\":\"Santa Cruz\",\"alias\":\"SC\",\"tablas\":[{\"_id\":\"6039390d8f00b52f08780408\",\"tabla\":\"Tabla 1\",\"libre\":true},{\"_id\":\"6039483445bc750a5ce98873\",\"tabla\":\"Tabla 3\",\"libre\":true},{\"_id\":\"6039450b0b62433674e6c5c9\",\"tabla\":\"Tabla 10\",\"libre\":true}],\"__v\":6}]}"
        val gson: Gson = GsonBuilder().create()
        val ranchos = gson.fromJson(data, ResponseRanchoList::class.java)
        return ranchos.ranchos
    }

    fun compareRanchos(internos: List<Rancho>, externos: List<Rancho>): List<List<Rancho>> {
        val inserts = externos.filter { externo ->
            internos.find { interno ->
                interno.idRancho == externo.idRancho } == null
        }
        val updates = externos.filter { externo ->
            internos.find { interno ->
                interno.idRancho == externo.idRancho } != null
        }
        val deletes = internos.filter { interno ->
            externos.find { externo ->
                interno.idRancho == externo.idRancho } == null
        }
        return listOf(inserts, updates, deletes)
    }

    fun insertRancho(rancho: Rancho) {
        val gson: Gson = GsonBuilder().create()
    }
}