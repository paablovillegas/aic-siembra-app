package mx.grupo.tepeyac.mexico.aic.siembra.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.SiembraApp
import javax.inject.Inject

class Main : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as SiembraApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getInfo()
    }
}