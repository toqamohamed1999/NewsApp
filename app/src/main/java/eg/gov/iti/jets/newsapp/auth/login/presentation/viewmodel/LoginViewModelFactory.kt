package eg.gov.iti.jets.newsapp.auth.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eg.gov.iti.jets.newsapp.auth.login.data.local.LoginRepoImpl

class LoginViewModelFactory(private val loginRepo: LoginRepoImpl) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            LoginViewModel(loginRepo) as T
        }else{
            throw IllegalArgumentException()
        }
    }
}