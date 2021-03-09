package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import retrofit2.Call
import retrofit2.http.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 08/03/21
 */
interface AsistenciaApi {
    @GET("asistencias")
    fun getAsistenciasGrupo(): Call<ResponseAsistenciaGrupoList>

    @POST("asistencias")
    fun insertAsistenciaGrupo(@Body body: SendAsistenciaGrupoItem): Call<ResponseAsistenciaGrupoItem>

    @PUT("asistencias/{id_asistencia}")
    fun updateAsistenciaGrupo(
        @Path(value = "id_asistencia") id: String,
        @Body rancho: SendAsistenciaGrupoItem,
    ): Call<ResponseAsistenciaGrupoItem>
}