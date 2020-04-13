package com.pshetye.covid19.ui.countries.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pshetye.apimodeler.di.interfaces.ProvideActivityComponent
import com.pshetye.covid19.R
import com.pshetye.covid19.di.components.Covid19Component
import com.pshetye.covid19.di.components.DaggerCovid19Component
import com.pshetye.covid19.ui.countries.recyclerview.CountriesAdapter
import com.pshetye.covid19.ui.countries.viewmodels.*
import kotlinx.android.synthetic.main.countries_bottom_sheet_main.*
import kotlinx.android.synthetic.main.countries_fragment_with_bottom_sheet.*
import javax.inject.Inject

class CountriesFragment : Fragment() {

    private lateinit var covid19Component: Covid19Component

    @Inject
    lateinit var countriesAdapter: CountriesAdapter
    @Inject
    lateinit var countriesViewModelFactory: CountriesViewModelFactory

    @SortedBy
    private var sortedBy: String = SORT_OPTION_CASES

    private val sharedViewDataModel: SharedViewModel by activityViewModels()

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
        updateSortedByText(view.context)

        setupRecyclerView(view)

        with(getViewModel()) {

            setupSwipeRefreshLayout(this, refresher)

            setupSharedViewModel(this, refresher)

            triggerRequest(this, refresher)
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
        with(countries) {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
    }

    private fun getViewModel(): CountriesViewModel = ViewModelProvider(this, countriesViewModelFactory)
        .get(CountriesViewModel::class.java)

    private fun setupSwipeRefreshLayout(viewModel: CountriesViewModel, swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchCovidStatus(sortedBy)
        }

        viewModel.covid19CasesPerCountry.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = false
            countriesAdapter.submitList(it)
            countries.smoothScrollToPosition(0)
        })
    }

    private fun setupSharedViewModel(viewModel: CountriesViewModel, swipeRefreshLayout: SwipeRefreshLayout) {
        sharedViewDataModel.sortOptionLiveData.observe(viewLifecycleOwner, Observer {
            sortedBy = it
            triggerRequest(viewModel, swipeRefreshLayout)
            updateSortedByText(swipeRefreshLayout.context)
        })
    }

    private fun triggerRequest(viewModel: CountriesViewModel, swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.isRefreshing = true
        viewModel.fetchCovidStatus(sortedBy)
    }

    private fun getSortedByStringRes(context: Context, @SortedBy sortedBy: String): String {
        @StringRes
        val sortedByStringRes: Int = when (sortedBy) {
            SORT_OPTION_CASES -> R.string.cases
            SORT_OPTION_RECOVERIES -> R.string.recoveries
            SORT_OPTION_CRITICAL -> R.string.critical
            SORT_OPTION_DEATHS -> R.string.deaths
            else -> throw IllegalStateException("Unsupported sort option")
        }
        return context.getString(sortedByStringRes)
    }

    private fun updateSortedByText(context: Context) {
        if (sortedBy == SORT_OPTION_ALPHABETICAL) {
            sorted_by.setText(R.string.sorted_alphabetically)
        } else {
            sorted_by.text = context.getString(
                R.string.sorted_by,
                getSortedByStringRes(context, sortedBy)
            )
        }
    }
}