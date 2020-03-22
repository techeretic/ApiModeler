package com.pshetye.apimodeler.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.apimodeler.R
import com.pshetye.apimodeler.di.components.FragmentComponent
import com.pshetye.apimodeler.di.interfaces.ProvideActivityComponent
import com.pshetye.apimodeler.ui.home.recyclerview.ModuleAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var fragmentComponent: FragmentComponent

    @Inject
    lateinit var moduleAdapter: ModuleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = inflater.context

        initializeFragmentComponent(context)
        fragmentComponent.inject(this)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.modules)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = moduleAdapter

        val homeViewModel: HomeViewModel by viewModels()
        homeViewModel.supportedModules.observe(viewLifecycleOwner, Observer {
            moduleAdapter.submitList(it)
        })
        return root
    }

    private fun initializeFragmentComponent(context: Context) {
        fragmentComponent = (context as ProvideActivityComponent).getActivityComponent()
            .getFragmentComponentFactory()
            .create()
    }
}