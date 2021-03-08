package mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad

import androidx.room.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 08/03/21
 */
@Dao
interface ActividadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(area: Actividad): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(area: List<Actividad>): List<Long>

    @Update
    fun update(area: Actividad)

    @Update
    fun update(area: List<Actividad>)

    @Delete
    fun delete(area: Actividad)

    @Delete
    fun delete(area: List<Actividad>)
}