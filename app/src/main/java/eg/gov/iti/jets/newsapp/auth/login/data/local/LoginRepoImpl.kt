package eg.gov.iti.jets.newsapp.auth.login.data.local

import eg.gov.iti.jets.newsapp.auth.login.data.model.LoginModel
import eg.gov.iti.jets.newsapp.auth.login.data.model.LoginResponse
import eg.gov.iti.jets.newsapp.auth.login.data.remote.LoginAPIInterface

class LoginRepoImpl (private val loginRepo:LoginAPIInterface):LoginAPIInterface {
    override suspend fun loginUser(key: String, body: LoginModel): LoginResponse {
       return loginRepo.loginUser(key, body)

    }
}