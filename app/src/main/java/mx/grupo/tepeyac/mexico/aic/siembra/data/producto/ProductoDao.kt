package mx.grupo.tepeyac.mexico.aic.siembra.data.producto

import androidx.room.*

@Dao
interface ProductoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(producto: Producto): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(producto: List<Producto>): List<Long>

    @Update
    fun update(producto: Producto)

    @Update
    fun update(producto: List<Producto>)

    @Delete
    fun delete(producto: Producto)

    @Delete
    fun delete(producto: List<Producto>)

    @Query("SELECT * FROM Producto WHERE id_producto IS NOT NULL")
    fun getProductos(): List<Producto>

    @Query("SELECT * FROM Producto WHERE id_producto IS NULL")
    fun getProductosNoSubidos(): List<Producto>
}