package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador

import androidx.room.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 08/03/21
 */
@Dao
interface TrabajadorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trabajador: Trabajador): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trabajadores: List<Trabajador>): List<Long>

    @Update
    fun update(trabajador: Trabajador)

    @Update
    fun update(trabajadores: List<Trabajador>)

    @Delete
    fun delete(trabajador: Trabajador)

    @Delete
    fun delete(trabajadores: List<Trabajador>)
}