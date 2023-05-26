package eg.gov.iti.jets.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eg.gov.iti.jets.newsapp.auth.domain.model.SignUpModel
import eg.gov.iti.jets.newsapp.auth.signup.data.model.SignUpResponse
import eg.gov.iti.jets.newsapp.base.remote.APIClient
import eg.gov.iti.jets.newsapp.newsscreen.data.local.ArticleLocalSource
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.ArticleSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.xml.transform.Source

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {


            val article =
                Article(source = ArticleSource("1","source1"),
                    author = "auther1",title = "title1", description = "desc1",url="url1",
                urlToImage = "", publishedAt = "", content = "sdkpokfssvv")

            val locale = ArticleLocalSource(applicationContext)
            val res = locale.insertArticle(article)
            println("######################## $res")
        }


    }
}