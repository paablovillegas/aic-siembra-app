package mx.grupo.tepeyac.mexico.aic.siembra.data.area

import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.Actividad

data class ResponseAreaList(
    val ok: Boolean,
    val areas: List<AreaItem>
)

data class ResponseAreaItem(
    val ok: Boolean,
    val area: AreaItem,
)

data class AreaItem(
    @SerializedName("_id")
    val id: String,
    val area: String,
    val actividades: List<ActividadItem>
) {
    fun toEntity(): Area =
        Area(
            idArea = id,
            area = area
        )

    fun toActividadesEntities(): List<Actividad> =
        actividades.map{
                act -> act.toEntity(id)
        }

    fun getAreaWithActividades(): AreaWithActividades =
        AreaWithActividades(
            area = toEntity(),
            actividades = toActividadesEntities()
        )
}

data class ActividadItem(
    @SerializedName("_id")
    val id: String,
    val actividad: String,
) {
    fun toEntity(idArea: String): Actividad =
        Actividad(
            idActividad = id,
            actividad = actividad,
            idArea = idArea,
        )
}