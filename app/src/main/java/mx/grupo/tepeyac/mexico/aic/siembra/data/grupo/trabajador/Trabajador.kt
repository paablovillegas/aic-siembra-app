package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.HashMap

@Entity
data class Trabajador(
    @PrimaryKey
    @ColumnInfo(name="id_interno")
    var idInterno: Long = 0,
    @ColumnInfo(name = "id_trabajador")
    var idTrabajador: String? = null,
    val nombres: String,
    @ColumnInfo( name = "apellido_paterno")
    val apellidoPaterno: String,
    @ColumnInfo( name = "apellido_materno")
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
)
//TODO: Agregar evidencias: Nueva tabla? ? ?