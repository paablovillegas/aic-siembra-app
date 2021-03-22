package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Trabajador(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    val id: Long = 0,
    @ColumnInfo(name = "id_trabajador")
    val idTrabajador: String? = null,
    var nombres: String,
    @ColumnInfo(name = "apellido_paterno")
    var apellidoPaterno: String,
    @ColumnInfo(name = "apellido_materno")
    var apellidoMaterno: String? = null,
    var nss: String? = null,
    var curp: String? = null,
    var rfc: String? = null,
    val ine: String? = null,
    var genero: String? = null,
    var domicilio: String? = null,
    @ColumnInfo(name = "fecha_alta")
    val fechaAlta: Date = Date(),
    @ColumnInfo(name = "fecha_nacimiento")
    val fechaNacimiento: Date? = null,
    val activo: Boolean = true,
    val tarjeta: Boolean = false,
    var sueldo: Double = 0.0,
    var extra: Double? = 0.0,
    var bono: Double? = 0.0,
    val editado: Boolean = false,
    @ColumnInfo(name = "id_grupo")
    val idGrupo: Long,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idTrabajador: String? = null,
        nombres: String = "",
        apellidoPaterno: String = "",
        apellidoMaterno: String? = null,
        nss: String? = null,
        curp: String? = null,
        rfc: String? = null,
        ine: String? = null,
        genero: String? = null,
        domicilio: String? = null,
        fechaAlta: Date = Date(),
        fechaNacimiento: Date? = null,
        activo: Boolean = true,
        tarjeta: Boolean = false,
        sueldo: Double = 220.0,
        extra: Double? = null,
        bono: Double? = null,
        editado: Boolean = false,
        idGrupo: Long,
    ) : this(
        id,
        idTrabajador,
        nombres,
        apellidoPaterno,
        apellidoMaterno,
        nss,
        curp,
        rfc,
        ine,
        genero,
        domicilio,
        fechaAlta,
        fechaNacimiento,
        activo,
        tarjeta,
        sueldo,
        extra,
        bono,
        editado,
        idGrupo,
        false,
    )

    fun getNombreCompleto(): String =
        if (apellidoMaterno == null) "$nombres $apellidoPaterno"
        else "$nombres $apellidoPaterno $apellidoMaterno"

    fun dataCorrect(): Boolean =
        nombres.isNotEmpty() && apellidoPaterno.isNotEmpty()
}
//TODO: Agregar evidencias: Nueva tabla? ? ?