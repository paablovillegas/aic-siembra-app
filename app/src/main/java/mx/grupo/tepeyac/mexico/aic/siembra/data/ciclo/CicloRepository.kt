package mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.AppDatabase
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.ProductoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CicloRepository(context: Context) {
    private val cicloDao: CicloDao = AppDatabase.getInstance(context).cicloDao
    private val ranchoRepository: RanchoRepository by lazy {
        RanchoRepository(context)
    }
    private val productoRepository: ProductoRepository by lazy {
        ProductoRepository(context)
    }
    private val cicloApi: CicloApi =
        ServiceGenerator.createService(context, CicloApi::class.java, "a")

    fun insert(ciclo: Ciclo) = cicloDao.insert(ciclo)

    fun getCiclos(): List<Ciclo> = cicloDao.getCiclos()
    fun getCiclosLD(idTabla: Long): LiveData<List<Ciclo>> = cicloDao.getCiclosLD(idTabla)
    fun getCiclo(idCiclo: Long): Ciclo = cicloDao.getCiclo(idCiclo)

    fun compareProductos(
        internos: List<Ciclo>,
        externos: List<Ciclo>
    ): List<Ciclo> {
        val inserts = externos.filter { externo ->
            internos.find { interno ->
                interno.idCiclo == externo.idCiclo
            } == null
        }
        val updates = externos.mapNotNull { externo ->
            val interno = internos.find { interno ->
                interno.idCiclo == externo.idCiclo && !interno.editado
            }
            if (interno != null) externo.copy(id = interno.id)
            else null
        }
        val deletes = internos.filter { interno ->
            externos.find { externo ->
                interno.idProducto == externo.idProducto
            } == null
        }.map { i -> i.copy(delete = true) }
        return listOf(inserts, updates, deletes).flatten()
    }

    private fun updateDatabaseCiclos(tablas: List<Ciclo>) {
        tablas.forEach { tabla ->
            when {
                tabla.delete -> cicloDao.delete(tabla)
                tabla.id > 0 -> cicloDao.update(tabla)
                else -> cicloDao.insert(tabla)
            }
        }
    }

    fun downloadCiclos() {
        cicloApi.getCiclos().enqueue(object : Callback<ResponseCicloList> {
            override fun onResponse(
                call: Call<ResponseCicloList>,
                response: Response<ResponseCicloList>
            ) {
                response.body()?.let { c ->
                    val ciclos = c.ciclos.mapNotNull { ciclo ->
                        val idTabla = ranchoRepository.getTablaID(ciclo.tabla)
                        val idProducto = productoRepository.getProductoID(ciclo.producto.id)
                        if (idTabla == null || idProducto == null)
                            return@mapNotNull null
                        return@mapNotNull ciclo.toEntity(idTabla, idProducto)
                    }
                    val data = compareProductos(getCiclos(), ciclos)
                    updateDatabaseCiclos(data)
                }
            }

            override fun onFailure(call: Call<ResponseCicloList>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun insertCiclo(ciclo: Ciclo) {
        val idTabla: String? = ranchoRepository.getTablaID(ciclo.idTabla)
        val idProducto: String? = productoRepository.getProductoID(ciclo.idProducto)
        if (idTabla == null || idProducto == null)
            return
        ciclo.toSendCicloItem(idTabla, idProducto).let { sci ->
            cicloApi.insertCiclo(sci).enqueue(object : Callback<ResponseCicloItem> {
                override fun onResponse(
                    call: Call<ResponseCicloItem>,
                    response: Response<ResponseCicloItem>
                ) {
                    response.body()?.let {
                        Log.i("TAG", "onResponse: $it")
                    }
                }

                override fun onFailure(call: Call<ResponseCicloItem>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    fun updateCiclo(ciclo: Ciclo) {
        val idTabla: String? = ranchoRepository.getTablaID(ciclo.idTabla)
        val idProducto: String? = productoRepository.getProductoID(ciclo.idProducto)
        if (idTabla == null || idProducto == null)
            return
        ciclo.idCiclo?.let { idCiclo ->
            ciclo.toSendCicloItem(idTabla, idProducto).let { sci ->
                cicloApi.updateCiclo(idCiclo, sci).enqueue(object : Callback<ResponseCicloItem> {
                    override fun onResponse(
                        call: Call<ResponseCicloItem>,
                        response: Response<ResponseCicloItem>
                    ) {
                        response.body()?.let {
                            Log.i("TAG", "onResponse: $it")
                        }
                    }

                    override fun onFailure(call: Call<ResponseCicloItem>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            }

        }

    }
}