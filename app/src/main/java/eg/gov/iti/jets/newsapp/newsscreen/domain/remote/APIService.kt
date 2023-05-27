package eg.gov.iti.jets.newsapp.newsscreen.domain.remote



import eg.gov.iti.jets.newsapp.base.remote.AppRetrofit
import eg.gov.iti.jets.newsapp.util.Constants

import retrofit2.Retrofit


object NewsRetrofit {

    val retrofit: Retrofit =  AppRetrofit.retrofit.newBuilder().baseUrl(Constants.BASE_URL_NEWS).build()
}
object APIClient{
    val newsAPIService : NewsAPIInterface by lazy {
        NewsRetrofit.retrofit.create(NewsAPIInterface::class.java)
    }

}