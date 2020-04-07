package com.pshetye.covid19.ui.countries.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pshetye.apimodeler.di.interfaces.ProvideActivityComponent
import com.pshetye.covid19.R
import com.pshetye.covid19.di.components.Covid19Component
import com.pshetye.covid19.di.components.DaggerCovid19Component
import com.pshetye.covid19.ui.countries.recyclerview.CountriesAdapter
import com.pshetye.covid19.ui.countries.viewmodels.CountriesViewModel
import com.pshetye.covid19.ui.countries.viewmodels.CountriesViewModelFactory
import kotlinx.android.synthetic.main.countries_bottom_sheet_main.*
import kotlinx.android.synthetic.main.countries_fragment_with_bottom_sheet.*
import javax.inject.Inject

class CountriesFragment : Fragment() {

    private lateinit var covid19Component: Covid19Component

    @Inject
    lateinit var countriesAdapter: CountriesAdapter
    @Inject
    lateinit var countriesViewModelFactory: CountriesViewModelFactory

    private var sortedBy = "cases"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initializeCovid19Component(inflater.context).inject(this)

        return inflater.inflate(R.layout.countries_fragment_with_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sorted_by.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_countries_to_sort)
        )
        sorted_by.text = view.context.getString(R.string.sorted_by, sortedBy)

        setupRecyclerView(view)

        with(getViewModel()) {

            setupSwipeRefreshLayout(this, refresher)

            triggerInitialRequest(this, refresher)
        }
    }

    private fun initializeCovid19Component(context: Context): Covid19Component {
        return DaggerCovid19Component.factory()
            .create((context as ProvideActivityComponent).getActivityComponent())
            .apply {
                covid19Component = this
            }
    }

    private fun setupRecyclerView(rootView: View) {
        rootView.findViewById<RecyclerView>(R.id.countries).apply {
            layoutManager = GridLayoutManager(context, 2).apply {
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
            adapter = countriesAdapter
        }
    }

    private fun getViewModel(): CountriesViewModel = ViewModelProvider(this, countriesViewModelFactory)
        .get(CountriesViewModel::class.java)

    private fun setupSwipeRefreshLayout(viewModel: CountriesViewModel, swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchCovidStatus()
        }

        viewModel.covid19CasesPerCountry.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = false
            countriesAdapter.submitList(it)
        })
    }

    private fun triggerInitialRequest(viewModel: CountriesViewModel, swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.isRefreshing = true
        viewModel.fetchCovidStatus()
    }
}