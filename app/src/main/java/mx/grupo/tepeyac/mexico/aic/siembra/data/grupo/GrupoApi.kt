package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo

import retrofit2.Call
import retrofit2.http.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 08/03/21
 */
interface GrupoApi {
    @GET("lugares_trabajo")
    fun getGrupos(): Call<ResponseGrupoList>

    @POST("lugares_trabajo")
    fun insertGrupo(@Body body: SendGrupoItem): Call<ResponseGrupoItem>

    @PUT("lugares_trabajo/{id}")
    fun updateGrupo(
        @Path(value = "id") id: String,
        @Body rancho: SendGrupoItem,
    ): Call<ResponseGrupoItem>
}