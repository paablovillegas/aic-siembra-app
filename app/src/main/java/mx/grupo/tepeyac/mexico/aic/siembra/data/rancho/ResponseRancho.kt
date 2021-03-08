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
        tablas.map { it.toEntity() }

    fun getRanchoWithTablas(): RanchoWithTablas =
        RanchoWithTablas(
            rancho = toEntity(),
            tablas = getTablasEntities()
        )
}

data class TablaItem(
    @SerializedName("_id")
    val id: String,
    val tabla: String,
) {
    fun toEntity(idRancho: Long = 0): Tabla =
        Tabla(
            idRancho = idRancho,
            idTabla = id,
            tabla = tabla
        )
}

data class SendRanchoItem(
    val idRancho: String?,
    val rancho: String,
    val alias: String?,
    val tablas: List<SendTablaItem>
) {
    constructor(rwt: RanchoWithTablas) :
            this(
                rwt.rancho.idRancho,
                rwt.rancho.rancho,
                rwt.rancho.alias,
                rwt.tablas.mapNotNull { it.toSendTablaItem() },
            )
}

data class SendTablaItem(
    @SerializedName("_id")
    val id: String?,
    val tabla: String,
)