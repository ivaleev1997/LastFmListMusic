package com.education.core

import com.education.core_api.mediator.NetworkProvider
import com.education.core_api.viewmodel.ViewModelsProvider
import com.education.core_impl.DaggerNetworkComponent
import com.education.core_impl.DaggerViewModelComponent

object CoreProviderFactory {

    fun createNetworkBuilder(): NetworkProvider {
        return DaggerNetworkComponent.create()
    }

    fun createViewModelBuilder(): ViewModelsProvider {
        return DaggerViewModelComponent.create()
    }
}