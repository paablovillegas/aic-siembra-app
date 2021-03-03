package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla

data class ResponseRanchoList(
    val ok: Boolean,
    val ranchos: List<RanchoItem>,
)

data class ResponseRanchoItem(
    val ok: Boolean,
    val rancho: RanchoItem,
)

data class RanchoItem(
    @SerializedName("_id")
    val id: String,
    val rancho: String,
    val alias: String?,
    val tablas: List<TablaItem>,
) {
    fun toEntity(): Rancho =
        Rancho(
            idRancho = id,
            rancho = rancho,
            alias = alias
        )

    fun getTablasEntities(): List<Tabla> =
        tablas.map { tabla ->
            tabla.toEntity(id)
        }

    fun getRanchoWithTablas(): RanchoWithTablas =
        RanchoWithTablas(
            rancho = toEntity(),
            tablas = getTablasEntities()
        )
}

data class TablaItem(
    val id: String,
    val tabla: String,
) {
    fun toEntity(idRancho: String): Tabla =
        Tabla(
            idRancho = idRancho,
            idTabla = id,
            tabla = tabla
        )
}