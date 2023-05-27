package eg.gov.iti.jets.newsapp.auth.login.data.remote


import eg.gov.iti.jets.newsapp.auth.login.data.model.LoginModel
import eg.gov.iti.jets.newsapp.auth.login.data.model.LoginResponse
import eg.gov.iti.jets.newsapp.util.Constants

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginAPIInterface {
    @POST(Constants.LOGIN_URL)
    suspend fun loginUser(@Query("key") key: String, @Body body: LoginModel): LoginResponse
}