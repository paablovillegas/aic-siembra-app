package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import androidx.room.*

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
}