package eg.gov.iti.jets.newsapp.profile.data.local

import android.util.Log
import eg.gov.iti.jets.newsapp.base.local.sharedPrefs.SharedOperations
import eg.gov.iti.jets.newsapp.profile.data.repo.ProfileRepo
import eg.gov.iti.jets.newsapp.profile.domain.local.ProfileLocalSourceInterface
import eg.gov.iti.jets.newsapp.profile.domain.model.UserModel

class ProfileLocalSource : ProfileLocalSourceInterface {
    companion object {
        @Volatile
        private var localSource: ProfileLocalSourceInterface? = null

        fun getLocalSourceInstance() = localSource ?: synchronized(this) {
            val temp = ProfileLocalSource()
            localSource = temp
            temp
        }

    }

    override suspend fun deleteUser(): Boolean {
        return SharedOperations.deleteCurrentUser()
    }

    override suspend fun getUserdata(): UserModel {
        val user = SharedOperations.getCurrentUserData()
        val userModel = UserModel(userName = user.first.toString(), email = user.third.toString())
        Log.e("TAG", user.first.toString() )
        Log.e("TAG", user.second.toString() )
        Log.e("TAG", user.third.toString() )
        return userModel
    }
}