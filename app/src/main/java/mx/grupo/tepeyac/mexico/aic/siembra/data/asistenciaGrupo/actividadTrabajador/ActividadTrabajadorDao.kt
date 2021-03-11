package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador

import androidx.room.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 09/03/21
 */
@Dao
interface ActividadTrabajadorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(actividadTrabajador: ActividadTrabajador): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(actividadTrabajador: List<ActividadTrabajador>): List<Long>

    @Update
    fun update(actividadTrabajador: ActividadTrabajador)

    @Update
    fun update(actividadTrabajador: List<ActividadTrabajador>)

    @Delete
    fun delete(actividadTrabajador: ActividadTrabajador)

    @Delete
    fun delete(actividadTrabajador: List<ActividadTrabajador>)
}