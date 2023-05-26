package eg.gov.iti.jets.newsapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


}