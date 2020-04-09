package com.pshetye.cacovid19.di.components

import com.pshetye.apimodeler.di.components.ActivityComponent
import com.pshetye.cacovid19.di.modules.NetworkModule
import com.pshetye.cacovid19.di.scopes.CaCovid19
import com.pshetye.cacovid19.ui.counties.fragments.CountiesFragment
import dagger.Component

@[
    CaCovid19
    Component(
        dependencies = [ActivityComponent::class],
        modules = [NetworkModule::class]
    )
]
interface CaCovid19Component {
    @Component.Factory
    interface Factory {
        fun create(activityComponent: ActivityComponent): CaCovid19Component
    }

    fun inject(countiesFragment: CountiesFragment)
}