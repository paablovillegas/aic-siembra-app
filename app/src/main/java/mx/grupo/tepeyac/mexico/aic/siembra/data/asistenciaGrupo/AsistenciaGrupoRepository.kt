package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class AsistenciaGrupoRepository {
    fun getAsistenciasGrupo(): List<AsistenciaGrupoItem> {
        val data = "{\"ok\":true,\"asistencias\":[{\"_id\":\"603d38b0360ee62e28ecdf3f\",\"lugar_trabajo\":{\"tipo_lugar\":\"cuadrilla\",\"_id\":\"603d07f42da8a318f86dfc74\",\"fletes\":{\"regular\":2200},\"lugar\":\"Juan Mata\",\"__v\":0},\"rancho\":{\"_id\":\"603935b0afbf59217c1afc1d\",\"rancho\":\"Santa Cruz\",\"alias\":\"SC\",\"__v\":6},\"fecha\":\"2021-03-01T06:00:00.000Z\",\"trabajadores\":[{\"_id\":\"603d42d19f68de3160700776\",\"trabajador\":{\"sueldo\":0,\"extra\":0,\"bono\":0,\"_id\":\"603d3300f8a6fb38386212e4\",\"salario\":0,\"nombres\":\"Luis Enrique\",\"apellido_paterno\":\"Cornejo\"},\"asistencia\":{\"_id\":\"603d42d19f68de3160700778\",\"actividades\":[],\"credencial\":\"GTM-LE-0101\",\"entrada\":\"2021-03-01T17:23:27.000Z\",\"salida\":\"2021-03-01T21:51:46.000Z\"},\"extras\":[],\"bonos\":[],\"descuentos\":[]},{\"_id\":\"603d47640e3bd61e2cdb23d7\",\"trabajador\":{\"sueldo\":220,\"extra\":0,\"bono\":0,\"_id\":\"603d473e0e3bd61e2cdb23d4\",\"nombres\":\"Danae\",\"apellido_paterno\":\"Villanueva\"},\"asistencia\":{\"_id\":\"603d47640e3bd61e2cdb23d9\",\"actividades\":[],\"credencial\":\"GTM-LE-0101\",\"entrada\":\"2021-03-01T17:23:27.000Z\"},\"extras\":[],\"bonos\":[],\"descuentos\":[]}],\"__v\":3}]}"
        val gson: Gson = GsonBuilder().create()
        val ranchoResponse = gson.fromJson(data, ResponseAsistenciaGrupoList::class.java)
        return ranchoResponse.asistencias
    }
}