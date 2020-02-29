package com.education.songslist.di

import androidx.lifecycle.ViewModel
import com.education.songslist.repository.LastFmAppRepository
import com.education.songslist.repository.LastFmAppRepositoryImpl
import com.education.songslist.viewmodel.SongsListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class SongsListModule {

    @Binds
    @Singleton
    abstract fun bindsLastFmAppReposiatory(lastFmAppRepository: LastFmAppRepositoryImpl): LastFmAppRepository

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun provideSongsListViewModel(
            map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>,
            lastFmAppRepository: LastFmAppRepository
            ): ViewModel = SongsListViewModel(lastFmAppRepository).also {
            map[SongsListViewModel::class.java] = it
        }

        @Provides
        @JvmStatic
        @Singleton
        fun provideTrigger(viewModel: ViewModel) = ViewModelTrigger()
    }
}

class ViewModelTrigger