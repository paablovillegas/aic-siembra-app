package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.grupo.tepeyac.mexico.aic.siembra.R

class Catalogos : AppCompatActivity() {
    lateinit var viewmodel: CatalogosViewModel
    private lateinit var fragmentContainerView: FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogos)

        viewmodel = ViewModelProvider(this).get(CatalogosViewModel::class.java)

        setSupportActionBar(findViewById(R.id.toolbar_catalogos))
        supportActionBar?.title = "Catalogos"

        fragmentContainerView = findViewById(R.id.fragment)

        val fab: FloatingActionButton = findViewById(R.id.fab_catalogos)
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController: NavController = host.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            fab.show()
            when (destination.id) {
                R.id.catalogos_fragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.title = "Catálogos"
                    fab.hide()
                }
                R.id.lista_ranchos -> supportActionBar?.title = "Ranchos"
                R.id.lista_tablas -> supportActionBar?.title = "Tablas"
                R.id.lista_ciclos -> supportActionBar?.title = "Ciclos"
                R.id.lista_areas -> supportActionBar?.title = "Areas"
                R.id.lista_actividades -> supportActionBar?.title = "Actividades"
                R.id.lista_productos -> supportActionBar?.title = "Productos"
                R.id.form_rancho -> {
                    supportActionBar?.title = "Formulario Rancho"
                    fab.hide()
                }
                R.id.form_tabla -> {
                    supportActionBar?.title = "Formulario Tabla"
                    fab.hide()
                }
                R.id.form_ciclo -> {
                    supportActionBar?.title = "Formulario Ciclo"
                    fab.hide()
                }
                R.id.form_producto -> {
                    supportActionBar?.title = "Formulario Producto"
                    fab.hide()
                }
                R.id.form_grupo -> {
                    supportActionBar?.title = "Formulario Grupo"
                    fab.hide()
                }
                R.id.form_trabajador -> {
                    supportActionBar?.title = "Formulario Trabajador"
                    fab.hide()
                }
            }
        }
        fab.setOnClickListener {
            navController.currentDestination?.let {
                when (it.id) {
                    R.id.lista_ranchos ->
                        navController.navigate(R.id.action_lista_ranchos_to_formRancho)
                    R.id.lista_tablas ->
                        it.arguments["id_rancho"]?.defaultValue.let { id ->
                            try {
                                val bundle = Bundle().apply {
                                    this.putLong("id_rancho", id as Long)
                                }
                                navController.navigate(
                                    R.id.action_lista_tablas_to_form_tabla,
                                    bundle
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    R.id.lista_ciclos ->
                        it.arguments["id_tabla"]?.defaultValue.let { id ->
                            try {
                                val bundle = Bundle().apply {
                                    this.putLong("id_tabla", id as Long)
                                }
                                navController.navigate(
                                    R.id.action_lista_ciclos_to_from_ciclo,
                                    bundle
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    R.id.lista_productos ->
                        navController.navigate(R.id.action_lista_productos_to_form_producto)
                    R.id.lista_areas ->
                        navController.navigate(R.id.action_lista_areas_to_form_area)
                    R.id.lista_actividades ->
                        it.arguments["id_area"]?.defaultValue.let { id ->
                            try {
                                val bundle = Bundle().apply {
                                    this.putLong("id_area", id as Long)
                                }
                                navController.navigate(
                                    R.id.action_lista_actividades_to_form_actividad,
                                    bundle
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    R.id.lista_grupos ->
                        navController.navigate(R.id.action_lista_grupos_to_form_grupo)
                    R.id.lista_trabajadores ->
                        it.arguments["id_grupo"]?.defaultValue.let { id ->
                            try {
                                val bundle = Bundle().apply {
                                    this.putLong("id_grupo", id as Long)
                                }
                                navController.navigate(
                                    R.id.action_lista_trabajadores_to_form_trabajador,
                                    bundle
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                }
            }
        }
        val swipeActualizar: SwipeRefreshLayout = findViewById(R.id.refresh_catalogos)
        swipeActualizar.setOnRefreshListener {
            downloadData()
            lifecycleScope.launch(Dispatchers.IO) {
                delay(500)
                withContext(Dispatchers.Main) {
                    swipeActualizar.isRefreshing = false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_catalogos, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> fragmentContainerView.findNavController().popBackStack()
            R.id.menu_actualizar -> downloadData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun downloadData() {
        fragmentContainerView.findNavController().currentDestination?.let {
            when (it.id) {
                R.id.lista_ranchos,
                R.id.lista_tablas,
                R.id.form_rancho,
                R.id.form_tabla,
                -> viewmodel.downloadRanchos()
                R.id.lista_ciclos,
                R.id.form_ciclo,
                -> viewmodel.downloadCiclos()
                R.id.lista_areas,
                R.id.lista_actividades,
                R.id.form_area,
                R.id.form_actividad,
                -> viewmodel.downloadAreas()
                R.id.lista_grupos,
                R.id.lista_trabajadores,
                R.id.form_grupo,
                R.id.form_trabajador,
                -> viewmodel.downloadGrupos()
                R.id.lista_productos,
                R.id.form_producto,
                -> viewmodel.downloadProductos()
            }
        }
    }
}