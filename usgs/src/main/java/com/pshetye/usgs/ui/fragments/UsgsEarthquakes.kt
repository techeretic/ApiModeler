package com.pshetye.usgs.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.apimodeler.di.interfaces.ProvideActivityComponent
import com.pshetye.usgs.R
import com.pshetye.usgs.di.components.DaggerUsgsComponent
import com.pshetye.usgs.di.components.UsgsComponent
import com.pshetye.usgs.ui.recyclerview.EarthquakesAdapter
import com.pshetye.usgs.ui.viewmodel.UsgsEarthquakesViewModel
import com.pshetye.usgs.ui.viewmodel.UsgsViewModelFactory
import javax.inject.Inject

class UsgsEarthquakes : Fragment() {

    @Inject
    lateinit var usgsViewModelFactory: UsgsViewModelFactory

    @Inject
    lateinit var earthquakesAdapter: EarthquakesAdapter

    private lateinit var usgsComponent: UsgsComponent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initializeUsgsComponent(inflater.context)
        usgsComponent.inject(this)

        val root = inflater.inflate(R.layout.usgs_earthquakes_fragment, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.earthquakes)
        recyclerView.layoutManager = LinearLayoutManager(inflater.context)
        recyclerView.adapter = earthquakesAdapter

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this, usgsViewModelFactory)
            .get(UsgsEarthquakesViewModel::class.java)
        viewModel.fetchEarthQuakes()

        viewModel.earthquakes.observe(viewLifecycleOwner, Observer {
            earthquakesAdapter.submitList(it)
        })
    }

    private fun initializeUsgsComponent(context: Context) {
        usgsComponent = DaggerUsgsComponent.factory()
            .create((context as ProvideActivityComponent).getActivityComponent())
    }

}