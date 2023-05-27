package eg.gov.iti.jets.newsapp.base.remote

import eg.gov.iti.jets.newsapp.auth.domain.model.SignUpModel
import eg.gov.iti.jets.newsapp.auth.signup.data.model.SignUpResponse
import eg.gov.iti.jets.newsapp.util.Constants.Companion.SIGNUP_URL
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


interface SignUpAPIInterface {
    @POST(SIGNUP_URL)
    suspend fun signUpUser(
        @Query("key") key: String,
        @Body body: SignUpModel
    ): SignUpResponse
}