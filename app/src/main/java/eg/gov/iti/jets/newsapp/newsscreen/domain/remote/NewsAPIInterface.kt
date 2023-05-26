package eg.gov.iti.jets.newsapp.newsscreen.domain.remote


import eg.gov.iti.jets.newsapp.BuildConfig
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.NewsModel
import eg.gov.iti.jets.newsapp.util.NEWS
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIInterface {
    @GET(NEWS)
    suspend fun getNews(
        @Query("country")
        countryCode : String = "US",
        @Query("apiKey")
        apiKey : String = BuildConfig.NEWS_KEY
    ): NewsModel

}