package eg.gov.iti.jets.newsapp.base.remote

import eg.gov.iti.jets.newsapp.auth.login.data.remote.LoginAPIInterface
import eg.gov.iti.jets.newsapp.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AppRetrofit {

    val retrofit: Retrofit =  Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

object APIClient{
    val signUpAPIService : SignUpAPIInterface by lazy {
        AppRetrofit.retrofit.create(SignUpAPIInterface::class.java)
    }
    val loginAPIService : LoginAPIInterface by lazy {
        AppRetrofit.retrofit.create(LoginAPIInterface::class.java)
    }
}