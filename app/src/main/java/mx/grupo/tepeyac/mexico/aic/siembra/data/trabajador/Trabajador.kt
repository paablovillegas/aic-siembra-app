package mx.grupo.tepeyac.mexico.aic.siembra.data.trabajador

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Trabajador(
    @PrimaryKey
    var id_interno: Long,
    var id_trabajador: String? = null,
    val nombres: String,
    val apellido_paterno: String,
    val apellido_materno: String? = null,
    val nss: String?,
    val curp: String? = null,
    val rfc: String? = null,
    val ine: String? = null,
    val genero: String,
    val domicilio: String? = null,
    val fecha_nacimiento: Date? = null,
    val fecha_alta: Date = Date(),
    val salario: Double = 0.0,
    val extra: Double = 0.0,
    val bono: Double = 0.0,
    val activo: Boolean = true,
    val editado: Boolean = false
)