package mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo

import retrofit2.Call
import retrofit2.http.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 08/03/21
 */
interface CicloApi {
    @GET("ciclos")
    fun getCiclos(): Call<ResponseCicloList>

    @POST("ciclos")
    fun insertCiclo(@Body body: SendCicloItem): Call<ResponseCicloItem>

    @PUT("ciclos/{id_ciclo}")
    fun updateCiclo(
        @Path(value = "id_ciclo") id: String,
        @Body rancho: SendCicloItem,
    ): Call<ResponseCicloItem>
}