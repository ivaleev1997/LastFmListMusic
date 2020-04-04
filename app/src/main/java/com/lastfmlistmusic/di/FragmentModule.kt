package com.lastfmlistmusic.di

import com.lastfmlistmusic.ui.songslist.SongsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeSongsListFragment(): SongsListFragment
}