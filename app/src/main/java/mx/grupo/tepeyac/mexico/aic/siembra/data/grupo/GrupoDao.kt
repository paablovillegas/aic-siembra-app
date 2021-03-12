package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo

import androidx.room.*

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

    @Transaction
    @Query("SELECT * FROM Grupo WHERE id_grupo IS NOT NULL")
    fun getGrupos(): List<GrupoWithTrabajadores>

    @Transaction
    @Query("SELECT * FROM Grupo WHERE id_grupo IS NULL")
    fun getGruposNoSubidos(): List<GrupoWithTrabajadores>
}