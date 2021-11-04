package com.pshetye.apimodeler

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pshetye.apimodeler.di.components.ActivityComponent
import com.pshetye.apimodeler.di.components.DaggerActivityComponent
import com.pshetye.apimodeler.di.interfaces.ProvideActivityComponent

class MainActivity : AppCompatActivity(R.layout.activity_main), ProvideActivityComponent {

    private lateinit var activityComponent: ActivityComponent

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeActivityComponent()

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home
        ))
        bottomNavigationView = findViewById(R.id.nav_view)
        with (findNavController(R.id.nav_host_fragment)) {
//            addOnDestinationChangedListener { _, destination, _ ->
//                if(destination.id == R.id.navigation_home) {
//                    showBottomNavigation()
//                } else {
//                    hideBottomNavigation()
//                }
//            }
            bottomNavigationView.setupWithNavController(this)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    override fun getActivityComponent(): ActivityComponent {
        return activityComponent
    }

    private fun initializeActivityComponent() {
        activityComponent = DaggerActivityComponent.factory().create(applicationContext)
    }

    private fun hideBottomNavigation() {
        // bottom_navigation is BottomNavigationView
        with(bottomNavigationView) {
            if (visibility == View.VISIBLE && alpha == 1f) {
                animate()
                    .alpha(0f)
                    .withEndAction { visibility = View.GONE }
                    .duration = 500
            }
        }
    }

    private fun showBottomNavigation() {
        // bottom_navigation is BottomNavigationView
        with(bottomNavigationView) {
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .duration = 500
        }
    }
}