package com.lastfmlistmusic.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lastfmlistmusic.ViewModelFactory
import com.lastfmlistmusic.ViewModelKey
import com.lastfmlistmusic.ui.songslist.SongsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindAppViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?

    @Binds
    @IntoMap
    @ViewModelKey(SongsListViewModel::class)
    abstract fun bindCocktailsMainViewModel(songsListViewModel: SongsListViewModel): ViewModel

}