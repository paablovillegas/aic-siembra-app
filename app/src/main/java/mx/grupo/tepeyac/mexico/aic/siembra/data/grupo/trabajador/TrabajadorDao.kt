package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador

import androidx.lifecycle.LiveData
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

    @Query(
        """
        SELECT t.* 
        FROM Trabajador t
        INNER JOIN AsistenciaGrupo ag ON ag.id_grupo = t.id_grupo
        WHERE ag.id_interno = :idAsistenciaGrupo"""
    )
    fun getTrabajadoresLD(idAsistenciaGrupo: Long): LiveData<List<Trabajador>>

    @Query("SELECT * FROM Trabajador WHERE id_interno = :id")
    fun getTrabajador(id: Long): Trabajador

    @Query("SELECT id_trabajador FROM Trabajador WHERE id_interno = :id")
    fun getTrabajadorID(id: Long): String?

    @Query("SELECT id_interno FROM Trabajador WHERE id_trabajador = :id")
    fun getTrabajadorID(id: String): Long?
}