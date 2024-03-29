package com.pshetye.covid19.ui.countries.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.button.MaterialButton
import com.pshetye.apimodeler.di.interfaces.ProvideActivityComponent
import com.pshetye.covid19.R
import com.pshetye.covid19.di.components.Covid19Component
import com.pshetye.covid19.di.components.DaggerCovid19Component
import com.pshetye.covid19.ui.countries.recyclerview.CountriesAdapter
import com.pshetye.covid19.ui.countries.recyclerview.decoration.BottomOffsetDecoration
import com.pshetye.covid19.ui.countries.viewmodels.*
import javax.inject.Inject

class CountriesFragment : Fragment() {

    private lateinit var covid19Component: Covid19Component

    private lateinit var refresher: SwipeRefreshLayout
    private lateinit var sortedByButton: MaterialButton
    private lateinit var search: MaterialButton
    private lateinit var countries: RecyclerView
    private lateinit var bottomSheet: LinearLayout

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
        refresher = view.findViewById(R.id.refresher)
        sortedByButton = view.findViewById(R.id.sorted_by)
        search = view.findViewById(R.id.search)
        countries = view.findViewById(R.id.countries)
        bottomSheet = view.findViewById(R.id.bottom_sheet)

        sortedByButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_countries_to_sort)
        )
        search.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_countries_to_search)
        )
        updateSortedByText(view.context)

        setupRecyclerView()

        with(getViewModel()) {

            setupSwipeRefreshLayout(this, refresher)

            setupSharedViewModel(this, refresher)

            triggerRequest(this, refresher)
        }
    }

    private fun initializeCovid19Component(context: Context): Covid19Component {
        covid19Component = DaggerCovid19Component.factory()
            .create((context as ProvideActivityComponent).getActivityComponent())
        return covid19Component
    }

    private fun setupRecyclerView() {
        with(countries) {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
            addItemDecoration(
                BottomOffsetDecoration(
                    resources.getDimensionPixelOffset(R.dimen.bottom_sheet_positive_margin)
                )
            )
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
            if (it.size > 1) {
                showBottomSheetOptions()
            }
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
            sortedByButton.setText(R.string.sorted_alphabetically)
        } else {
            sortedByButton.text = context.getString(
                R.string.sorted_by,
                getSortedByStringRes(context, sortedBy)
            )
        }
    }

    private fun showBottomSheetOptions() {
        // bottom_navigation is BottomNavigationView
        with(bottomSheet) {
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .duration = 500
        }
    }
}