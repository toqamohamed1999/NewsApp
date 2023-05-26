package eg.gov.iti.jets.newsapp.auth.login.data.remote

import eg.gov.iti.jets.newsapp.auth.login.data.model.LoginResponse

sealed class LoginResponseState{
    class Success(val response: LoginResponse): LoginResponseState()
    class Loading(val message:String = "loading"):LoginResponseState()
    class Error(val error:String="Error"):LoginResponseState()
}
