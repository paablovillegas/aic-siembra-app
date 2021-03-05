package mx.grupo.tepeyac.mexico.aic.siembra.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.user.User

class LoginViewModel : ViewModel() {
    private val user = MutableLiveData<User>()
    val userLD: LiveData<User> = user

    companion object {
        const val TAG = "LogInVM"
    }


}