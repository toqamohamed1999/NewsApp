package eg.gov.iti.jets.newsapp.auth.domain.repo

import eg.gov.iti.jets.newsapp.BuildConfig
import eg.gov.iti.jets.newsapp.auth.domain.model.SignUpModel
import eg.gov.iti.jets.newsapp.auth.domain.model.User
import eg.gov.iti.jets.newsapp.auth.signup.data.model.SignUpResponse

interface AuthRepo {
    suspend fun signUpSignUpModel(
        key: String,
        userModel : SignUpModel
    ): SignUpResponse


}