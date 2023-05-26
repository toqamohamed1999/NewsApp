package eg.gov.iti.jets.newsapp.auth.data.repo

import eg.gov.iti.jets.newsapp.auth.domain.model.SignUpModel
import eg.gov.iti.jets.newsapp.auth.domain.repo.AuthRepo
import eg.gov.iti.jets.newsapp.auth.signup.data.model.SignUpResponse
import eg.gov.iti.jets.newsapp.base.local.sharedPrefs.SharedOperations
import eg.gov.iti.jets.newsapp.base.remote.SignUpAPIInterface

class RepoImpl(private val signUpAPIInterface: SignUpAPIInterface):AuthRepo {
    override suspend fun signUpSignUpModel(key: String, userMode: SignUpModel): SignUpResponse {
      return signUpAPIInterface.signUpUser(key,userMode)
    }
}