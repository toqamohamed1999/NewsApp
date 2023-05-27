package eg.gov.iti.jets.newsapp.profile.domain

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import eg.gov.iti.jets.newsapp.MainRule
import eg.gov.iti.jets.newsapp.base.local.sharedPrefs.SharedOperations
import eg.gov.iti.jets.newsapp.profile.data.FakeLocalDataSource
import eg.gov.iti.jets.newsapp.profile.data.repo.ProfileRepo
import eg.gov.iti.jets.newsapp.profile.domain.model.UserModel
import eg.gov.iti.jets.newsapp.profile.domain.repo.ProfileRepoInterface
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.core.Is
import org.junit.*
import org.junit.runner.RunWith
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RepoTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainRule()

    private var fakeLocalDataSource: FakeLocalDataSource? = null
    private var repo: ProfileRepo? = null
    private var userModel: UserModel? = null
    private var context: Application? = null


    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Application>()
        SharedOperations.initSharedPrefs(context!!)
        userModel = UserModel("arwa", "arwashams@gmail.com")
        fakeLocalDataSource = FakeLocalDataSource(userModel!!)
        repo = ProfileRepo.getProfileRepoInstance(fakeLocalDataSource!!)
    }

    @After
    fun tearDown() {
        fakeLocalDataSource = null
        repo = null
        userModel = null
    }

    @Test
    fun getUser_UserModel_Nothing() = mainCoroutineRule.runBlockingTest {
        val userModel = repo?.getUser()
        Assert.assertThat(userModel, notNullValue())
    }

    @Test
    fun deleteUserModel_Nothing_True() = mainCoroutineRule.runBlockingTest {
        val isCleared = repo?.deleteUser()
        Assert.assertThat(isCleared, Is.`is`(true))
    }


}