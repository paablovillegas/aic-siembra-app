package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia

import androidx.room.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 09/03/21
 */
@Dao
interface AsistenciaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asistencia: Asistencia): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asistencia: List<Asistencia>): List<Long>

    @Update
    fun update(asistencia: Asistencia)

    @Update
    fun update(asistencia: List<Asistencia>)

    @Delete
    fun delete(asistencia: Asistencia)

    @Delete
    fun delete(asistencia: List<Asistencia>)
}