package eg.gov.iti.jets.newsapp.newsscreen.domain.data

import eg.gov.iti.jets.newsapp.MainRule
import eg.gov.iti.jets.newsapp.newsscreen.domain.remote.NewsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RepoImplTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainRule = MainRule()

    lateinit var newsRepo : NewsRepo
    lateinit var fakeNewsAPIInterface : FackNewsAPIInterface
    @Before
    fun setUp() {
        fakeNewsAPIInterface = FackNewsAPIInterface()
        newsRepo = RepoImpl(fakeNewsAPIInterface)
        newsRepo = RepoImpl(fakeNewsAPIInterface)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testNewsOverNetwork() =mainRule.runBlockingTest {
            var result = newsRepo.getNews()
            //assertNotNull(result)
            assertEquals(fakeNewsAPIInterface.fakeNewsModel.articles.size, result.articles.size)
    }
}