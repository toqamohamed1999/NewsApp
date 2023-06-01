package eg.gov.iti.jets.newsapp.profile.data

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import eg.gov.iti.jets.newsapp.base.local.sharedPrefs.SharedOperations
import eg.gov.iti.jets.newsapp.profile.domain.local.ProfileLocalSourceInterface
import eg.gov.iti.jets.newsapp.profile.domain.model.UserModel
import org.junit.Before
import org.junit.Test

class FakeLocalDataSource(
    private val userModel: UserModel
) : ProfileLocalSourceInterface {

    private var context: Application? = null

    @Test
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Application>()
        SharedOperations.initSharedPrefs(context!!)
    }

    @Test
    private fun tearDown() {
        context = null
    }

    override suspend fun deleteUser(): Boolean {
        return SharedOperations.deleteCurrentUser()
    }

    override suspend fun getUserdata(): UserModel {
        return userModel
    }
}