package com.lastfmlistmusic

import com.education.core_api.mediator.SongsListMediator
import com.education.songslist.SongsListMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface MediatorBindings {

    @Binds
    @Reusable
    fun bindsSongsListMediator(songsListMediatorImpl: SongsListMediatorImpl): SongsListMediator
}