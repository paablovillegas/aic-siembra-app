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
    val nombres: String,
    @ColumnInfo(name = "apellido_paterno")
    val apellidoPaterno: String,
    @ColumnInfo(name = "apellido_materno")
    val apellidoMaterno: String? = null,
    val nss: String? = null,
    val curp: String? = null,
    val rfc: String? = null,
    val ine: String? = null,
    val genero: String? = null,
    val domicilio: String? = null,
    @ColumnInfo(name = "fecha_alta")
    val fechaAlta: Date = Date(),
    @ColumnInfo(name = "fecha_nacimiento")
    val fechaNacimiento: Date? = null,
    val activo: Boolean = true,
    val tarjeta: Boolean = false,
    val sueldo: Double = 0.0,
    val extra: Double? = 0.0,
    val bono: Double? = 0.0,
    val editado: Boolean = false,
    @ColumnInfo(name = "id_grupo")
    val idGrupo: Long,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idTrabajador: String? = null,
        nombres: String,
        apellidoPaterno: String,
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
        sueldo: Double = 0.0,
        extra: Double? = 0.0,
        bono: Double? = 0.0,
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

}
//TODO: Agregar evidencias: Nueva tabla? ? ?