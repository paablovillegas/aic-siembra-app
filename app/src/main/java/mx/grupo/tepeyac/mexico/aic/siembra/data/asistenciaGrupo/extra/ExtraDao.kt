package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra

import androidx.room.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 09/03/21
 */
@Dao
interface ExtraDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(extra: Extra): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(extra: List<Extra>): List<Long>

    @Update
    fun update(extra: Extra)

    @Update
    fun update(extra: List<Extra>)

    @Delete
    fun delete(extra: Extra)

    @Delete
    fun delete(extra: List<Extra>)
}