package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TablaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tabla: Tabla): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tabla: List<Tabla>): List<Long>

    @Update
    fun update(tabla: Tabla)

    @Update
    fun update(tabla: List<Tabla>)

    @Delete
    fun delete(tabla: Tabla)

    @Delete
    fun delete(tabla: List<Tabla>)

    @Query(" SELECT id_interno FROM Tabla WHERE id_tabla = :id")
    fun getTablaID(id: String): Long?

    @Query(" SELECT id_tabla FROM Tabla WHERE id_interno = :id")
    fun getTablaID(id: Long): String?

    @Query(" SELECT * FROM Tabla WHERE id_interno = :id")
    fun getTabla(id: Long): Tabla

    @Query(" SELECT * FROM Tabla WHERE id_rancho = :id")
    fun getTablasLD(id: Long): LiveData<List<Tabla>>

    @Query(" SELECT * FROM Tabla WHERE id_rancho = :id")
    fun getTablas(id: Long): List<Tabla>
}