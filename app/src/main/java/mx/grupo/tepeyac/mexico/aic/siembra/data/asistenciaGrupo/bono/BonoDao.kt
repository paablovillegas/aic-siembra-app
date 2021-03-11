package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono

import androidx.room.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 09/03/21
 */
@Dao
interface BonoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bono: Bono): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bono: List<Bono>): List<Long>

    @Update
    fun update(bono: Bono)

    @Update
    fun update(bono: List<Bono>)

    @Delete
    fun delete(bono: Bono)

    @Delete
    fun delete(bono: List<Bono>)
}