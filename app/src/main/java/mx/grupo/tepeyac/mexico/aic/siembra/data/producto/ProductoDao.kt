package mx.grupo.tepeyac.mexico.aic.siembra.data.producto

import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM Producto")
    fun getProductosLD(): LiveData<List<Producto>>

    @Query("SELECT * FROM Producto WHERE id_producto IS NOT NULL")
    fun getProductos(): List<Producto>

    @Query("SELECT * FROM Producto WHERE id_producto IS NULL")
    fun getProductosNoSubidos(): List<Producto>

    @Query("SELECT id_interno FROM Producto WHERE id_producto = :id")
    fun getProductoID(id: String): Long?

    @Query("SELECT id_producto FROM Producto WHERE id_interno = :id")
    fun getProductoID(id: Long): String?

    @Query("SELECT * FROM Producto WHERE id_interno = :id")
    fun getProducto(id: Long): Producto
}