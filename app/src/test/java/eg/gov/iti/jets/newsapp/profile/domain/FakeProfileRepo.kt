package eg.gov.iti.jets.newsapp.profile.domain

import android.content.SharedPreferences
import eg.gov.iti.jets.newsapp.base.local.sharedPrefs.SharedOperations
import eg.gov.iti.jets.newsapp.profile.data.FakeLocalDataSource
import eg.gov.iti.jets.newsapp.profile.data.repo.ProfileRepo
import eg.gov.iti.jets.newsapp.profile.domain.local.ProfileLocalSourceInterface
import eg.gov.iti.jets.newsapp.profile.domain.model.UserModel
import eg.gov.iti.jets.newsapp.profile.domain.repo.ProfileRepoInterface

class FakeProfileRepo(
    private val localDataSource: ProfileLocalSourceInterface = FakeLocalDataSource(
        UserModel(
            "arwa",
            "arwashams643@gmail.com"
        )
    ),
) : ProfileRepoInterface {


    override suspend fun deleteUser(): Boolean {
        return SharedOperations.deleteCurrentUser()
    }

    override suspend fun getUser(): UserModel {
        return localDataSource.getUserdata()
    }
}