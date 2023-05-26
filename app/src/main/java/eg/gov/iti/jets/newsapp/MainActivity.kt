package eg.gov.iti.jets.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eg.gov.iti.jets.newsapp.auth.domain.model.SignUpModel
import eg.gov.iti.jets.newsapp.auth.signup.data.model.SignUpResponse
import eg.gov.iti.jets.newsapp.base.remote.APIClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
}