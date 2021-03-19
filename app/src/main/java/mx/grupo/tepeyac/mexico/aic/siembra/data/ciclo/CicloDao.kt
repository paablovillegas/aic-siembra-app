package mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo

import androidx.lifecycle.LiveData
import androidx.room.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 08/03/21
 */
@Dao
interface CicloDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ciclo: Ciclo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ciclo: List<Ciclo>): List<Long>

    @Update
    fun update(ciclo: Ciclo)

    @Update
    fun update(ciclo: List<Ciclo>)

    @Delete
    fun delete(ciclo: Ciclo)

    @Delete
    fun delete(ciclo: List<Ciclo>)

    @Query("SELECT * FROM Ciclo WHERE id_tabla = :idTabla")
    fun getCiclosLD(idTabla: Long): LiveData<List<Ciclo>>

    @Query("SELECT * FROM Ciclo WHERE id_ciclo IS NOT NULL")
    fun getCiclos(): List<Ciclo>

    @Query("SELECT * FROM Ciclo WHERE id_interno = :id")
    fun getCiclo(id: Long): Ciclo

    @Query("SELECT * FROM Ciclo WHERE id_ciclo IS NULL")
    fun getCiclosNoSubidos(): List<Ciclo>
}