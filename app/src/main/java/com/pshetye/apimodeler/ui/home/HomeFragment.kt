package com.pshetye.apimodeler.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pshetye.apimodeler.R
import com.pshetye.apimodeler.di.components.FragmentComponent
import com.pshetye.apimodeler.di.interfaces.ProvideActivityComponent
import com.pshetye.apimodeler.ui.home.recyclerview.ModuleAdapter
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
        initializeFragmentComponent(inflater.context).inject(this)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        modules.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true)
        modules.adapter = moduleAdapter

        val homeViewModel: HomeViewModel by viewModels()
        homeViewModel.supportedModules.observe(viewLifecycleOwner, Observer {
            moduleAdapter.submitList(it)
        })
    }

    private fun initializeFragmentComponent(context: Context): FragmentComponent =
        (context as ProvideActivityComponent).getActivityComponent()
            .getFragmentComponentFactory()
            .create()
            .apply {
                fragmentComponent = this
                return fragmentComponent
            }
}