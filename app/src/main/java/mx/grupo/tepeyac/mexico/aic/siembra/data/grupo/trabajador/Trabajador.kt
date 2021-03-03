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
    var idInterno: Long,
    @SerializedName("_id")
    @ColumnInfo(name = "id_trabajador")
    var idTrabajador: String? = null,
    val nombres: String,
    @SerializedName("apellido_paterno")
    @ColumnInfo( name = "apellido_paterno")
    val apellidoPaterno: String,
    @SerializedName("apellido_materno")
    @ColumnInfo( name = "apellido_materno")
    val apellidoMaterno: String? = null,
    val nss: String? = null,
    val curp: String? = null,
    val rfc: String? = null,
    val ine: String? = null,
    val genero: String? = null,
    val domicilio: String? = null,
    @SerializedName("fecha_alta")
    @ColumnInfo(name = "fecha_alta")
    val fechaAlta: Date = Date(),
    @SerializedName("fecha_naciemiento")
    @ColumnInfo(name = "fecha_nacimiento")
    val fechaNacimiento: Date? = null,
    val activo: Boolean = true,
    val tarjeta: Boolean = false,
    val sueldo: Double = 0.0,
    val extra: Double = 0.0,
    val bono: Double = 0.0,
    val editado: Boolean = false,
    @Ignore
    val salario: HashMap<String, Any>,
    @Ignore
    val evidencias: HashMap<String, Any>,

)