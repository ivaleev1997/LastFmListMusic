package com.education.songslist.di

import com.education.core.CoreProviderFactory
import com.education.core_api.mediator.ProvidersFacade
import com.education.core_api.viewmodel.ViewModelsProvider
import com.education.songslist.SongsListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [SongsListModule::class],
    dependencies = [ProvidersFacade::class, ViewModelsProvider::class]
)
interface SongsListComponent: ViewModelsProvider {

    companion object {
        fun create(providersFacade: ProvidersFacade): SongsListComponent {
            return DaggerSongsListComponent
                .builder()
                .viewModelsProvider(CoreProviderFactory.createViewModelBuilder())
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(songsListFragment: SongsListFragment)
}