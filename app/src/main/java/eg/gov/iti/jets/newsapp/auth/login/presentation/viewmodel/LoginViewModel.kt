package eg.gov.iti.jets.newsapp.auth.login.presentation.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eg.gov.iti.jets.newsapp.auth.login.data.local.LoginRepoImpl
import eg.gov.iti.jets.newsapp.auth.login.data.model.LoginModel
import eg.gov.iti.jets.newsapp.auth.login.data.model.LoginResponse
import eg.gov.iti.jets.newsapp.auth.login.data.remote.LoginAPIInterface
import eg.gov.iti.jets.newsapp.auth.login.data.remote.LoginResponseState
import eg.gov.iti.jets.newsapp.base.local.sharedPrefs.SharedOperations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Thread.State

class LoginViewModel(private val loginRepo:LoginAPIInterface): ViewModel() {

    private  var _loginState:MutableStateFlow<LoginResponseState> = MutableStateFlow(LoginResponseState.Loading())
    var loginState:StateFlow<LoginResponseState> = _loginState
    private var _userExists:MutableStateFlow<Boolean> = MutableStateFlow(false)
    var userExists :StateFlow<Boolean> = _userExists
    init {
        checkUserInSharedPrefs()
    }
    fun login(key:String,body:LoginModel){
        viewModelScope.launch {
         try {
           _loginState.value =  LoginResponseState.Success(loginRepo.loginUser(key, body))
         }catch(e:java.lang.Exception){
             _loginState.value = LoginResponseState.Error()
         }
        }
    }
   private fun checkUserInSharedPrefs() {
       val userData = SharedOperations.getCurrentUserData()
        _userExists.value = !userData.second.isNullOrBlank()
    }
    fun saveUserDataToSharedPrefs(response: LoginResponse)
    {
        SharedOperations.setCurrentUserData(response.displayName,response.idToken,response.email)
    }
    fun validateEmail(email:String):Boolean{
        return  Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun validatePassword(password:String):Boolean{
       return password.count() >= 8
    }
}