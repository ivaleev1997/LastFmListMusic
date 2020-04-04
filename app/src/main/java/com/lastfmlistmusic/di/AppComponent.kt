package com.lastfmlistmusic.di

import com.lastfmlistmusic.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    MainActivityModule::class,
    ViewModelModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}