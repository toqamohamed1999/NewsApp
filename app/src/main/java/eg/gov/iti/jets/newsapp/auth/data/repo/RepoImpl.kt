package eg.gov.iti.jets.newsapp.auth.data.repo

import eg.gov.iti.jets.newsapp.auth.domain.model.SignUpModel
import eg.gov.iti.jets.newsapp.auth.domain.repo.AuthRepo
import eg.gov.iti.jets.newsapp.auth.signup.data.model.SignUpResponse

class RepoImpl():AuthRepo {
    override suspend fun signUpSignUpModel(key: String, userMode: SignUpModel): SignUpResponse {
        TODO("Not yet implemented")
    }
}