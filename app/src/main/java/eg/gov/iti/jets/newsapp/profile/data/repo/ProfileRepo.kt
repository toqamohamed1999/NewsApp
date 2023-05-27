package eg.gov.iti.jets.newsapp.profile.data.repo

import eg.gov.iti.jets.newsapp.profile.domain.local.ProfileLocalSourceInterface
import eg.gov.iti.jets.newsapp.profile.domain.model.UserModel
import eg.gov.iti.jets.newsapp.profile.domain.repo.ProfileRepoInterface

class ProfileRepo private constructor(val profileLocalSource: ProfileLocalSourceInterface) :
    ProfileRepoInterface {

    companion object {
        @Volatile
        private var repoInstance: ProfileRepo? = null
        fun getProfileRepoInstance(localSource: ProfileLocalSourceInterface) =
            repoInstance ?: synchronized(this) {
                val temp = ProfileRepo(localSource)
                this.repoInstance = temp
                temp
            }
    }


    override suspend fun deleteUser(): Boolean {
        return profileLocalSource.deleteUser()
    }

    override suspend fun getUser(): UserModel {
        return profileLocalSource.getUserdata()
    }
}