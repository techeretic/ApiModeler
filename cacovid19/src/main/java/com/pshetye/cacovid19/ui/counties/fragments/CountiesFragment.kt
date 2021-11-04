package com.pshetye.cacovid19.ui.counties.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pshetye.apimodeler.di.interfaces.ProvideActivityComponent
import com.pshetye.cacovid19.R
import com.pshetye.cacovid19.di.components.CaCovid19Component
import com.pshetye.cacovid19.di.components.DaggerCaCovid19Component
import com.pshetye.cacovid19.ui.counties.recyclerview.CountiesAdapter
import com.pshetye.cacovid19.ui.counties.viewmodels.CountiesViewModel
import com.pshetye.cacovid19.ui.counties.viewmodels.CountiesViewModelFactory
import javax.inject.Inject

class CountiesFragment : Fragment() {

    private lateinit var caCovid19Component: CaCovid19Component

    private lateinit var refresher: SwipeRefreshLayout

    @Inject
    lateinit var countiesviewmodelFactory: CountiesViewModelFactory
    @Inject
    lateinit var countiesAdapter: CountiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initializeComponent(inflater.context).inject(this)
        return inflater.inflate(R.layout.counties_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listOfCountries = view.findViewById<RecyclerView>(R.id.list_of_counties)
        refresher = view.findViewById(R.id.refresher)
        with(listOfCountries) {
            layoutManager = GridLayoutManager(view.context, 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position == 0) {
                            2
                        } else {
                            1
                        }
                    }
                }
            }
            adapter = countiesAdapter
        }
        with(getViewModel()) {
            countiesLiveData.observe(viewLifecycleOwner, Observer {
                refresher.isRefreshing = false
                countiesAdapter.submitList(it)
            })

            refresher.setOnRefreshListener {
                triggetNetworkRequest(this)
            }

            triggetNetworkRequest(this)
        }
    }

    private fun triggetNetworkRequest(viewModel: CountiesViewModel) {
        refresher.isRefreshing = true
        viewModel.fetchCaliforniaCovidStatsViaSql()
    }

    private fun getViewModel(): CountiesViewModel =
        ViewModelProvider(this, countiesviewmodelFactory).get(CountiesViewModel::class.java)

    private fun initializeComponent(context: Context): CaCovid19Component {
        caCovid19Component = DaggerCaCovid19Component.factory()
            .create((context as ProvideActivityComponent).getActivityComponent())
        return caCovid19Component
    }
}