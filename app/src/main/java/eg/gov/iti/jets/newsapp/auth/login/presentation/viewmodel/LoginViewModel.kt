package eg.gov.iti.jets.newsapp.auth.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eg.gov.iti.jets.newsapp.auth.login.data.local.LoginRepoImpl
import eg.gov.iti.jets.newsapp.auth.login.data.model.LoginModel
import eg.gov.iti.jets.newsapp.auth.login.data.model.LoginResponse
import eg.gov.iti.jets.newsapp.auth.login.data.remote.LoginResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepo:LoginRepoImpl): ViewModel() {

    private  var _loginState:MutableStateFlow<LoginResponseState> = MutableStateFlow(LoginResponseState.Loading())
    var loginState:StateFlow<LoginResponseState> = _loginState
    fun login(key:String,body:LoginModel){
        viewModelScope.launch {
         try {
           _loginState.value =  LoginResponseState.Success(loginRepo.loginUser(key, body))
         }catch(e:java.lang.Exception){
             _loginState.value = LoginResponseState.Error()
         }
        }
    }
    fun validateEmail(email:String):Boolean{
    return true
    }
    fun validatePassword(password:String):Boolean{
        return true
    }
}