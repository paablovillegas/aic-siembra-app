package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros

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

class Registros : AppCompatActivity() {
    lateinit var viewmodel: RegistrosViewModel
    private lateinit var fragmentContainerView: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registros)

        viewmodel = ViewModelProvider(this).get(RegistrosViewModel::class.java)

        setSupportActionBar(findViewById(R.id.toolbar_registros))
        supportActionBar?.title = "Registros"

        fragmentContainerView = findViewById(R.id.fragment)

        val fab: FloatingActionButton = findViewById(R.id.fab_registros)
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController: NavController = host.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            fab.show()
            when (destination.id) {
                R.id.registros_fragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.title = "Registros"
                    fab.hide()
                }
            }
        }
        fab.setOnClickListener {
        }
        val swipeActualizar: SwipeRefreshLayout = findViewById(R.id.refresh_registros)
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
            }
        }
    }
}