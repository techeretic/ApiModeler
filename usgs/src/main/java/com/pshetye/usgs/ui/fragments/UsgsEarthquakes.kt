package com.pshetye.usgs.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.pshetye.apimodeler.di.interfaces.ProvideActivityComponent
import com.pshetye.usgs.R
import com.pshetye.usgs.di.components.DaggerUsgsComponent
import com.pshetye.usgs.di.components.UsgsComponent
import com.pshetye.usgs.ui.viewmodel.UsgsEarthquakesViewModel
import com.pshetye.usgs.ui.viewmodel.UsgsViewModelFactory
import javax.inject.Inject

class UsgsEarthquakes : Fragment() {

    @Inject
    lateinit var usgsViewModelFactory: UsgsViewModelFactory

    private lateinit var usgsComponent: UsgsComponent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initializeUsgsComponent(inflater.context)
        usgsComponent.inject(this)
        return inflater.inflate(R.layout.usgs_earthquakes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this, usgsViewModelFactory)
            .get(UsgsEarthquakesViewModel::class.java)
        viewModel.fetchEarthQuakes()
    }

    private fun initializeUsgsComponent(context: Context) {
        usgsComponent = DaggerUsgsComponent.factory()
            .create((context as ProvideActivityComponent).getActivityComponent())
    }

}