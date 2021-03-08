package mx.grupo.tepeyac.mexico.aic.siembra.data.area

import retrofit2.Call
import retrofit2.http.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 08/03/21
 */
interface AreaApi {
    @GET("areas")
    fun getAreas(): Call<ResponseAreaList>

    @POST("areas")
    fun insertArea(@Body body: SendAreaItem): Call<ResponseAreaItem>

    @PUT("areas/{id_area}")
    fun updateArea(
        @Path(value = "id_area") id: String,
        @Body area: SendAreaItem,
    ): Call<ResponseAreaItem>

}