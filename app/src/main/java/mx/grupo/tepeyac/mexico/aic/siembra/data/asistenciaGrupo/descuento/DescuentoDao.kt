package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento

import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM Descuento WHERE id_interno = :id")
    fun getDescuento(id: Long): Descuento

    @Transaction
    @Query("""
        SELECT d.*
        FROM Descuento d
        INNER JOIN AsistenciaGrupo ag ON ag.id_interno = d.id_asistencia_grupo
        WHERE ag.id_interno = :idAsistenciaGrupo
    """)
    fun getAsistenciasGrupo(idAsistenciaGrupo: Long): LiveData<List<DescuentoWithTrabajador>>
}