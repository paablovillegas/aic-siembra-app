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
    @SerializedName("lugar")
    val grupo: String,
    @SerializedName("tipo_lugar")
    val tipoGrupo: String,
    val fletes: ResponseFlete?,
    val trabajadores: List<Any>,
) {
    fun toEntity(): Grupo =
        if (fletes != null) {
            Grupo(
                idGrupo = id,
                grupo = grupo,
                tipoGrupo = tipoGrupo,
                fleteRegular = fletes.regular,
                fletePersona = fletes.persona,
                fleteCabraliego = fletes.cabraliego,
                fleteEscondida = fletes.cabraliego,
                fleteIncompleto = fletes.incompleto,
            )
        } else {
            Grupo(
                idGrupo = id,
                grupo = grupo,
                tipoGrupo = tipoGrupo
            )
        }
}

data class ResponseFlete(
    val regular: Double?,
    val persona: Double?,
    val cabraliego: Double?,
    val incompleto: Double?,
    val escondida: Double?,
)

data class ResponseTrabajador(
    @SerializedName("_id")
    var id: String,
    val nombres: String,
    @SerializedName("apellido_paterno")
    val apellidoPaterno: String,
    @SerializedName("apellido_materno")
    val apellidoMaterno: String?,
    val nss: String?,
    val curp: String?,
    val rfc: String?,
    val ine: String?,
    val genero: String?,
    val domicilio: String?,
    @SerializedName("fecha_alta")
    val fechaAlta: Date,
    @SerializedName("fecha_naciemiento")
    val fechaNacimiento: Date?,
    val activo: Boolean,
    val tarjeta: Boolean,
    val salario: ResponseSalario,
    val evidencias: ResponseEvidencias?,
) {
    fun toEntity(): Trabajador =
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
        )
}

data class ResponseSalario(
    val sueldo: Double,
    val extra: Double?,
    val bono: Double?,
)

data class ResponseEvidencias(
    val perfil: String?,
    val ine: String?,
)