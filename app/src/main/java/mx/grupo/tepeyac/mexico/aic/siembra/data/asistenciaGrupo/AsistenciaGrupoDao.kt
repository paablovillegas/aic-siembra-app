package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

/*
 * @author pablovillegas
 * @version 1.5
 * @since 08/03/21
 */
@Dao
interface AsistenciaGrupoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asistenciaGrupo: AsistenciaGrupo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asistenciaGrupo: List<AsistenciaGrupo>): List<Long>

    @Update
    fun update(asistenciaGrupo: AsistenciaGrupo)

    @Update
    fun update(asistenciaGrupo: List<AsistenciaGrupo>)

    @Delete
    fun delete(asistenciaGrupo: AsistenciaGrupo)

    @Delete
    fun delete(asistenciaGrupo: List<AsistenciaGrupo>)

    @Transaction
    @Query("SELECT * FROM AsistenciaGrupo WHERE id_asistencia_grupo IS NOT NULL")
    fun getAsistenciaGrupos(): List<AsistenciaGrupoWithInfo>

    @Transaction
    @Query("SELECT * FROM AsistenciaGrupo WHERE id_asistencia_grupo IS NULL")
    fun getAsistenciaGruposNoSubidos(): List<AsistenciaGrupoWithInfo>

    @Transaction
    @Query("SELECT * FROM AsistenciaGrupo WHERE fecha >= :start AND fecha < :end")
    fun getGruposAsistencias(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithAsistencias>>

    @Transaction
    @Query("SELECT * FROM AsistenciaGrupo WHERE fecha >= :start AND fecha < :end")
    fun getGruposActividades(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithActividades>>

    @Transaction
    @Query("SELECT * FROM AsistenciaGrupo WHERE fecha >= :start AND fecha < :end")
    fun getGruposExtras(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithExtras>>

    @Transaction
    @Query("SELECT * FROM AsistenciaGrupo WHERE fecha >= :start AND fecha < :end")
    fun getGruposBonos(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithBonos>>

    @Transaction
    @Query("SELECT * FROM AsistenciaGrupo WHERE fecha >= :start AND fecha < :end")
    fun getGruposDescuentos(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithDescuentos>>
}