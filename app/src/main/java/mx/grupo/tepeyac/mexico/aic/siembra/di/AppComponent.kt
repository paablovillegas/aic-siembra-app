package mx.grupo.tepeyac.mexico.aic.siembra.di

import android.app.Activity
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import mx.grupo.tepeyac.mexico.aic.siembra.ui.main.Main

@Component
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    fun inject(activity: Main)

}