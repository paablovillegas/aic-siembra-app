package mx.grupo.tepeyac.mexico.aic.siembra.data.area

import androidx.room.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 08/03/21
 */
@Dao
interface AreaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(area: Area): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(area: List<Area>): List<Long>

    @Update
    fun update(area: Area)

    @Update
    fun update(area: List<Area>)

    @Delete
    fun delete(area: Area)

    @Delete
    fun delete(area: List<Area>)

    @Transaction
    @Query("SELECT * FROM Area WHERE id_area IS NOT NULL")
    fun getAreas(): List<AreaWithActividades>

    @Transaction
    @Query("SELECT * FROM Area WHERE id_area IS NULL")
    fun getAreasNoSubidos(): List<AreaWithActividades>
}