package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import mx.grupo.tepeyac.mexico.aic.siembra.R

class Catalogos : AppCompatActivity() {
    lateinit var viewmodel: CatalogosViewModel
    lateinit var fragmentContainerView: FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogos)

        viewmodel = ViewModelProvider(this).get(CatalogosViewModel::class.java)

        setSupportActionBar(findViewById(R.id.toolbar_catalogos))
        supportActionBar?.title = "Catalogos"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fragmentContainerView = findViewById(R.id.fragment)

        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController: NavController = host.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.catalogos_fragment -> supportActionBar?.setTitle("Catálogos")
                R.id.lista_ranchos -> supportActionBar?.setTitle("Ranchos")
                R.id.lista_tablas -> supportActionBar?.setTitle("Tablas")
                R.id.lista_ciclos -> supportActionBar?.setTitle("Ciclos")
                R.id.lista_areas -> supportActionBar?.setTitle("Areas")
                R.id.lista_actividades -> supportActionBar?.setTitle("Actividades")
                R.id.lista_productos -> supportActionBar?.setTitle("Productos")
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
            R.id.menu_actualizar -> {
                fragmentContainerView.findNavController().currentDestination?.let {
                    when (it.id) {
                        R.id.lista_ranchos,
                        R.id.lista_tablas -> viewmodel.downloadRanchos()
                        R.id.lista_ciclos -> viewmodel.downloadCiclos()
                        R.id.lista_areas,
                        R.id.lista_actividades -> viewmodel.downloadAreas()
                        R.id.lista_grupos,
                        R.id.lista_trabajadores -> viewmodel.downloadGrupos()
                        R.id.lista_productos -> viewmodel.downloadProductos()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}