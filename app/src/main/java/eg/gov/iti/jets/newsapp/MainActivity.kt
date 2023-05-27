package eg.gov.iti.jets.newsapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import eg.gov.iti.jets.newsapp.util.MyNetworkStatus
import eg.gov.iti.jets.newsapp.util.NetworkConnectivityObserver
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var networkTextView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        networkTextView = findViewById(R.id.noInterNetTextView)
        observeNetwork()
        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        bottomNavigation = findViewById(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
        setUpNavBottom(navController)

    }

    private fun setUpNavBottom(navController: NavController) {
        navController.addOnDestinationChangedListener { _, navDestination, _ ->
            if (navDestination.id == R.id.signUpFragment || navDestination.id == R.id.loginFragment
            ) {
                bottomNavigation.visibility = View.GONE
            } else {
                bottomNavigation.visibility = View.VISIBLE
            }
        }
    }

    private fun observeNetwork() {
        NetworkConnectivityObserver.observeNetworkConnection().onEach {
            if (it == MyNetworkStatus.Available) {
                Log.e("MAIN0", "network nw is $it")
                networkTextView.visibility = View.GONE
            } else {


                networkTextView.visibility = View.VISIBLE
                Log.e("MAIN0", "network nw is $it")
            }
        }.launchIn(lifecycleScope)
    }


}