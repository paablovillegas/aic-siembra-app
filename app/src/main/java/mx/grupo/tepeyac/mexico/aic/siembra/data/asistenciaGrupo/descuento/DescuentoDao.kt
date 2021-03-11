package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento

import androidx.room.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 09/03/21
 */
@Dao
interface DescuentoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(descuento: Descuento): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(descuento: List<Descuento>): List<Long>

    @Update
    fun update(descuento: Descuento)

    @Update
    fun update(descuento: List<Descuento>)

    @Delete
    fun delete(descuento: Descuento)

    @Delete
    fun delete(descuento: List<Descuento>)
}