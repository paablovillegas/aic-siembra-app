package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo

import androidx.lifecycle.LiveData
import androidx.room.*
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.AsistenciaGrupoWithAsistencias
import java.util.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 08/03/21
 */
@Dao
interface GrupoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(grupo: Grupo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(grupo: List<Grupo>): List<Long>

    @Update
    fun update(grupo: Grupo)

    @Update
    fun update(grupo: List<Grupo>)

    @Delete
    fun delete(grupo: Grupo)

    @Delete
    fun delete(grupo: List<Grupo>)

    @Query("SELECT * FROM Grupo")
    fun getGruposLD(): LiveData<List<GrupoWithTrabajadores>>

    @Query("SELECT * FROM Grupo WHERE id_interno = :id")
    fun getGrupo(id: Long): Grupo

    @Query("SELECT id_grupo FROM Grupo WHERE id_interno = :id")
    fun getGrupoID(id: Long): String?

    @Query("SELECT id_interno FROM Grupo WHERE id_interno = :id")
    fun getGrupoID(id: String): Long?

    @Transaction
    @Query("SELECT * FROM Grupo WHERE id_interno = :id")
    fun getGrupoLD(id: Long): LiveData<GrupoWithTrabajadores>

    @Transaction
    @Query("SELECT * FROM Grupo WHERE id_grupo IS NOT NULL")
    fun getGrupos(): List<GrupoWithTrabajadores>

    @Transaction
    @Query("SELECT * FROM Grupo WHERE id_grupo IS NULL")
    fun getGruposNoSubidos(): List<GrupoWithTrabajadores>

    @Transaction
    @Query("""
        SELECT g.* FROM Grupo g
        LEFT JOIN AsistenciaGrupo ag ON ag.id_grupo = g.id_interno
            AND ag.fecha >= :start AND ag.fecha < :end
        WHERE ag.id_interno IS NULL
    """)
    fun getGruposDisponibles(start: Date, end: Date): List<Grupo>
}