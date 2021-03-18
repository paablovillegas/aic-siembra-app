package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import androidx.lifecycle.LiveData
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
    @Query("SELECT * FROM Rancho")
    fun getRanchosLD(): LiveData<List<RanchoWithTablas>>

    @Transaction
    @Query("SELECT * FROM Rancho WHERE id_interno = :idRancho")
    fun getRanchosLD(idRancho: Long): LiveData<RanchoWithTablas>

    @Transaction
    @Query("SELECT * FROM Rancho WHERE id_rancho IS NOT NULL")
    fun getRanchos(): List<RanchoWithTablas>

    @Transaction
    @Query("SELECT * FROM Rancho WHERE id_rancho IS NULL")
    fun getRanchosNoSubidos(): List<RanchoWithTablas>

    @Query("SELECT id_rancho FROM Rancho WHERE id_interno = :id")
    fun getRanchoID(id: Long): String?

    @Query("SELECT id_interno FROM Rancho WHERE id_rancho = :id")
    fun getRanchoID(id: String): Long?

}