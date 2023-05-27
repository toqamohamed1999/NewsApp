package eg.gov.iti.jets.newsapp.profile.domain.repo

import eg.gov.iti.jets.newsapp.profile.domain.model.UserModel

interface ProfileRepoInterface {

    suspend fun deleteUser() : Boolean
    suspend fun getUser() : UserModel
}