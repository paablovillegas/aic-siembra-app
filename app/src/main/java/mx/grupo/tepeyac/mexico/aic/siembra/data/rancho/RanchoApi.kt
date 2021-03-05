package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import retrofit2.Call
import retrofit2.http.*

interface RanchoApi {
    @GET("ranchos")
    fun getRanchos(): Call<ResponseRanchoList>

    @POST("ranchos")
    fun insertRancho(@Body body: SendRanchoItem): Call<ResponseRanchoItem>

    @PUT("ranchos/{id_rancho}")
    fun updateRancho(
        @Path(value = "id_rancho") id: String,
        @Body rancho: SendRanchoItem,
    ): Call<ResponseRanchoItem>
}