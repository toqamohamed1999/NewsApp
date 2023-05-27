package eg.gov.iti.jets.newsapp.profile.domain.local

import eg.gov.iti.jets.newsapp.profile.domain.model.UserModel

interface ProfileLocalSourceInterface {
    suspend fun deleteUser() : Boolean
    suspend fun getUserdata() : UserModel
}