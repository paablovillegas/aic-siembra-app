package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla

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
}