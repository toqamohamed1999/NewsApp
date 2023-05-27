package eg.gov.iti.jets.newsapp.profile.presentation.viewModel

import android.app.Application
import android.provider.ContactsContract.Profile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import eg.gov.iti.jets.newsapp.MainRule
import eg.gov.iti.jets.newsapp.base.local.sharedPrefs.SharedOperations
import eg.gov.iti.jets.newsapp.profile.data.FakeLocalDataSource
import eg.gov.iti.jets.newsapp.profile.domain.FakeProfileRepo
import eg.gov.iti.jets.newsapp.profile.domain.model.UserModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.core.Is
import org.junit.*
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ProfileViewModelTest {

    private var fakeLocalDataSource: FakeLocalDataSource? = null
    private lateinit var profileViewModel: ProfileViewModel
    private var repo: FakeProfileRepo? = null
    private var userModel: UserModel? = null
    private var context: Application? = null

    @get:Rule
    @ExperimentalCoroutinesApi
    val main = MainRule()

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Application>()
        SharedOperations.initSharedPrefs(context!!)
        userModel = UserModel("arwa", "arwashams643@gmail.com")
        fakeLocalDataSource = FakeLocalDataSource(userModel!!)
        repo = FakeProfileRepo(localDataSource = fakeLocalDataSource!!)
        profileViewModel = ProfileViewModel(repo!!)
    }


    @After
    fun tearDown() {
        userModel = null
        fakeLocalDataSource = null
        repo = null
    }

    @Test
    fun getUser() = main.runBlockingTest {
        val user = repo?.getUser()
        Assert.assertThat(user, notNullValue())


    }

    @Test
    fun deleteUser() = main.runBlockingTest {
        // Given : Alert object
        val user = UserModel("arwa", "arwa@gmail.com")

        // When : Call function from fake repo
        val result = repo?.deleteUser()

        Assert.assertThat(result, Is.`is`(true))


    }

}