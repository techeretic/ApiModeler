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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
        initializeUsgsComponent(inflater.context).inject(this)

        val root = inflater.inflate(R.layout.usgs_earthquakes_fragment, container, false)

        setupRecyclerView(root)

        with(getViewModel()) {
            val swipeRefreshLayout = root.findViewById<SwipeRefreshLayout>(R.id.refresher)

            setupSwipeRefreshLayout(this, swipeRefreshLayout)

            triggerInitialRequest(this, swipeRefreshLayout)
        }

        return root
    }

    private fun initializeUsgsComponent(context: Context): UsgsComponent {
        usgsComponent = DaggerUsgsComponent.factory()
            .create((context as ProvideActivityComponent).getActivityComponent())
        return usgsComponent
    }

    private fun setupRecyclerView(rootView: View) =
        with(rootView.findViewById<RecyclerView>(R.id.earthquakes)) {
            layoutManager = LinearLayoutManager(rootView.context)
            adapter = earthquakesAdapter
        }

    private fun getViewModel(): UsgsEarthquakesViewModel =
        ViewModelProvider(this, usgsViewModelFactory).get(UsgsEarthquakesViewModel::class.java)

    private fun setupSwipeRefreshLayout(
        viewModel: UsgsEarthquakesViewModel,
        swipeRefreshLayout: SwipeRefreshLayout
    ) {
        swipeRefreshLayout.setOnRefreshListener {
            earthquakesAdapter.submitList(emptyList())
            viewModel.fetchEarthQuakes()
        }
        viewModel.earthquakes.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = false
            earthquakesAdapter.submitList(it)
        })
    }

    private fun triggerInitialRequest(
        viewModel: UsgsEarthquakesViewModel,
        swipeRefreshLayout: SwipeRefreshLayout
    ) {
        swipeRefreshLayout.isRefreshing = true
        viewModel.fetchEarthQuakes()
    }
}