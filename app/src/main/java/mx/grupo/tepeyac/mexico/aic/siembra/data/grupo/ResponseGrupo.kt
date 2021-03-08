package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo

import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import java.util.*

data class ResponseGrupoList(
    val ok: Boolean,
    @SerializedName("lugares")
    val grupos: List<GrupoItem>,
)

data class ResponseGrupoItem(
    val ok: Boolean,
    @SerializedName("lugar")
    val grupo: GrupoItem,
)

data class GrupoItem(
    @SerializedName("_id")
    val id: String,
    @SerializedName("lugar", alternate = ["grupo"])
    val grupo: String,
    @SerializedName("tipo_lugar", alternate = ["tipo_grupo"])
    val tipoGrupo: String,
    val fletes: ResponseFlete?,
    val trabajadores: List<ResponseTrabajador>,
) {
    fun toEntity(): Grupo =
        Grupo(
            idGrupo = id,
            grupo = grupo,
            tipoGrupo = tipoGrupo,
            fleteRegular = fletes?.regular,
            fletePersona = fletes?.persona,
            fleteCabraliego = fletes?.cabraliego,
            fleteEscondida = fletes?.cabraliego,
            fleteIncompleto = fletes?.incompleto,
        )

    fun toTrabajadoresEntities(): List<Trabajador> =
        trabajadores.map { it.toEntity() }

    fun getGrupoWithTrabajadores(): GrupoWithTrabajadores =
        GrupoWithTrabajadores(
            grupo = toEntity(),
            trabajadores = toTrabajadoresEntities()
        )
}

data class ResponseFlete(
    val regular: Double? = null,
    val persona: Double? = null,
    val cabraliego: Double? = null,
    val incompleto: Double? = null,
    val escondida: Double? = null,
)

data class ResponseTrabajador(
    @SerializedName("_id")
    var id: String,
    val nombres: String,
    @SerializedName("apellido_paterno")
    val apellidoPaterno: String,
    @SerializedName("apellido_materno")
    val apellidoMaterno: String? = null,
    val nss: String? = null,
    val curp: String? = null,
    val rfc: String? = null,
    val ine: String? = null,
    val genero: String? = null,
    val domicilio: String? = null,
    @SerializedName("fecha_alta")
    val fechaAlta: Date,
    @SerializedName("fecha_naciemiento")
    val fechaNacimiento: Date? = null,
    val activo: Boolean = true,
    val tarjeta: Boolean = false,
    val salario: ResponseSalario,
    val evidencias: ResponseEvidencias? = null,
) {
    fun toEntity(idGrupo: Long = 0): Trabajador =
        Trabajador(
            idTrabajador = id,
            nombres = nombres,
            apellidoPaterno = apellidoPaterno,
            apellidoMaterno = apellidoMaterno,
            nss = nss,
            curp = curp,
            rfc = rfc,
            ine = ine,
            genero = genero,
            domicilio = domicilio,
            fechaAlta = fechaAlta,
            fechaNacimiento = fechaNacimiento,
            activo = activo,
            tarjeta = tarjeta,
            sueldo = salario.sueldo,
            extra = salario.extra,
            bono = salario.bono,
            idGrupo = idGrupo
        )
}

data class ResponseSalario(
    val sueldo: Double,
    val extra: Double? = null,
    val bono: Double? = null,
)

data class ResponseEvidencias(
    val perfil: String?,
    val ine: String?,
)

data class SendGrupoItem(
    val idGrupo: String?,
    @SerializedName("lugar")
    val grupo: String,
    val flete: ResponseFlete,
    @SerializedName("tipo_lugar")
    val tipoGrupo: String,
    val trabajadores: List<String>
) {
    constructor(gwt: GrupoWithTrabajadores) :
            this(
                gwt.grupo.idGrupo,
                gwt.grupo.grupo,
                gwt.getFletes(),
                gwt.grupo.tipoGrupo,
                gwt.trabajadores.mapNotNull { it.idTrabajador },
            )
}