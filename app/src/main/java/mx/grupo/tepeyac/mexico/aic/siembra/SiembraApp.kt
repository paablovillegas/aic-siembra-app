package mx.grupo.tepeyac.mexico.aic.siembra

import android.app.Application
import mx.grupo.tepeyac.mexico.aic.siembra.di.AppComponent
import mx.grupo.tepeyac.mexico.aic.siembra.di.DaggerAppComponent

class SiembraApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}