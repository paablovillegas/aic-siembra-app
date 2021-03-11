package mx.grupo.tepeyac.mexico.aic.siembra.utils

import androidx.room.TypeConverter
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador.TipoActividadTrabajador

/*
 * @author pablovillegas
 * @version 1.5
 * @since 09/03/21
 */
class DatabaseTipoActividadTrabajadorConverter {
    @TypeConverter
    fun toTipoActividadTrabajador(value: Int?): TipoActividadTrabajador? {
        return when(value) {
            1 -> TipoActividadTrabajador.REGULAR
            2 -> TipoActividadTrabajador.EXTRA
            3 -> TipoActividadTrabajador.BONO
            else -> null
        }
    }

    @TypeConverter
    fun fromTipoActividadTrabajador(tipo: TipoActividadTrabajador?): Int? {
        return when(tipo) {
            TipoActividadTrabajador.REGULAR -> 1
            TipoActividadTrabajador.EXTRA -> 2
            TipoActividadTrabajador.BONO -> 3
            else -> null
        }
    }
}