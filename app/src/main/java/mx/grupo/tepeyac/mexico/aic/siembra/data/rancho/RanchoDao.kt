package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import androidx.room.*

@Dao
interface RanchoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rancho: Rancho): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rancho: List<Rancho>): List<Long>

    @Update
    fun update(rancho: Rancho)

    @Update
    fun update(rancho: List<Rancho>)

    @Delete
    fun delete(rancho: Rancho)

    @Delete
    fun delete(rancho: List<Rancho>)

    @Transaction
    @Query("SELECT * FROM Rancho WHERE id_rancho IS NOT NULL")
    fun getRanchos(): List<RanchoWithTablas>
}