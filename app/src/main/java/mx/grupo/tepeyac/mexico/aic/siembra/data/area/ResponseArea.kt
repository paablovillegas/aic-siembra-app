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
        actividades.map { act ->
            act.toEntity()
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
    fun toEntity(idArea: Long = 0): Actividad =
        Actividad(
            idActividad = id,
            actividad = actividad,
            idArea = idArea,
        )
}

data class SendAreaItem(
    val idArea: String?,
    val area: String,
    val actividades: List<SendActividadItem>
) {
    constructor(awa: AreaWithActividades) :
            this(
                awa.area.idArea,
                awa.area.area,
                awa.actividades.map { it.toSendActividadItem() }
            )
}

data class SendActividadItem(
    @SerializedName("_id")
    val idActividad: String?,
    val actividad: String,
)