package eg.gov.iti.jets.newsapp.auth.signup.data.repo

import eg.gov.iti.jets.newsapp.auth.domain.model.SignUpModel
import eg.gov.iti.jets.newsapp.auth.signup.data.model.SignUpResponse
import eg.gov.iti.jets.newsapp.base.remote.SignUpAPIInterface

class SignupRepoImpl(private val signUpRepo:SignUpAPIInterface) : SignUpAPIInterface {
    override suspend fun signUpUser(key: String, body: SignUpModel): SignUpResponse {
       return  signUpRepo.signUpUser(key,body)
    }
}