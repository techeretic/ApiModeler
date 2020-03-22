package com.pshetye.apimodeler.di.components

import com.pshetye.apimodeler.di.scopes.PerFragment
import com.pshetye.apimodeler.ui.home.HomeFragment
import dagger.Subcomponent

@[PerFragment Subcomponent]
interface FragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }

    fun inject(homeFragment: HomeFragment)
}