package compose.multiplatform.template.splash.impl.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    // Multiplatform ViewModel: works the same on Android and iOS.

    fun something(){
        viewModelScope.launch {

        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}
