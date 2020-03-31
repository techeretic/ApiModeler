package com.pshetye.covid19.ui.countries.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.apimodeler.di.interfaces.ProvideActivityComponent
import com.pshetye.covid19.R
import com.pshetye.covid19.di.components.Covid19Component
import com.pshetye.covid19.di.components.DaggerCovid19Component
import com.pshetye.covid19.ui.countries.recyclerview.CountriesAdapter
import com.pshetye.covid19.ui.countries.viewmodels.CountriesViewModel
import com.pshetye.covid19.ui.countries.viewmodels.CountriesViewModelFactory
import javax.inject.Inject

class CountriesFragment : Fragment() {

    private lateinit var covid19Component: Covid19Component

    @Inject
    lateinit var countriesAdapter: CountriesAdapter
    @Inject
    lateinit var countriesViewModelFactory: CountriesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initializeCovid19Component(inflater.context).inject(this)

        val rootView = inflater.inflate(R.layout.countries_fragment, container, false)

        setupRecyclerView(rootView)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(getViewModel()) {
            fetchCovidStatus()

            covid19CasesPerCountry.observe(viewLifecycleOwner, Observer {
                countriesAdapter.submitList(it)
            })
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
            layoutManager = LinearLayoutManager(rootView.context)
            adapter = countriesAdapter
        }
    }

    private fun getViewModel(): CountriesViewModel = ViewModelProvider(this, countriesViewModelFactory)
        .get(CountriesViewModel::class.java)
}