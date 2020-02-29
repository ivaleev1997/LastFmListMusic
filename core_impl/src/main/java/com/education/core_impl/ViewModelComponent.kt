package com.education.core_impl

import com.education.core_api.viewmodel.ViewModelsProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class]
)
interface ViewModelComponent : ViewModelsProvider