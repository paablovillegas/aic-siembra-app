package mx.grupo.tepeyac.mexico.aic.siembra.data.producto

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class ProductoRepositoryTest {
    companion object {
        val PRODUCTO_ITERNO_1 = listOf(
            Producto(1, "60294fc5d479631958ac62ce", "Esparrago"),
            Producto(1, "60397652f5e0a43f2018f93e", "Brocoli"),
        )
        val PRODUCTO_EXTERNO_1 = listOf(
            ProductoItem("60394fc5d475741958ac62ce", "Lechuga"),
            ProductoItem("60397652f5e0a43f2018f93e", "Brocoli"),
        )
    }

    @Test
    fun parseProductos() {
        val repo = ProductoRepository()
        val productos = repo.getProductos()
        assertThat(productos.size, `is`(1))
    }

    @Test
    fun compareProducto1Producto2_allCorrect() {
        val repo = ProductoRepository()
        val areas = PRODUCTO_EXTERNO_1.map { it.toEntity() }
        val productos = repo.compareProductos(PRODUCTO_ITERNO_1, areas)
        val productosEliminadas = productos.count { i -> i.delete }
        val productosActualizadas = productos.count { i -> i.id > 0L && !i.delete }
        val productosInsertadas = productos.count { i -> i.id == 0L && !i.delete }
        assertThat(productosEliminadas, `is`(1))
        assertThat(productosActualizadas, `is`(1))
        assertThat(productosInsertadas, `is`(1))
    }
}