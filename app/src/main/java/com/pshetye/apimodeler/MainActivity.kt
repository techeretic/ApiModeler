package com.pshetye.apimodeler

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.pshetye.apimodeler.di.components.ActivityComponent
import com.pshetye.apimodeler.di.components.DaggerActivityComponent
import com.pshetye.apimodeler.di.interfaces.ProvideActivityComponent
import com.pshetye.apimodeler.ui.home.recyclerview.ModuleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), ProvideActivityComponent {

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeActivityComponent()

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home
        ))
        with (findNavController(R.id.nav_host_fragment)) {
            setupActionBarWithNavController(this, appBarConfiguration)
            nav_view.setupWithNavController(this)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    override fun getActivityComponent(): ActivityComponent {
        return activityComponent
    }

    private fun initializeActivityComponent() {
        activityComponent = DaggerActivityComponent.factory().create()
    }
}