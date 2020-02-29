package com.lastfmlistmusic

import android.app.Application
import com.education.core_api.mediator.AppWithFacade
import com.education.core_api.mediator.ProvidersFacade

class App: Application(), AppWithFacade {

    companion object {

        private var facadeComponent: FacadeComponent? = null
    }

    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(this).also {
            facadeComponent = it
        }
    }

    override fun onCreate() {
        super.onCreate()
        (getFacade() as FacadeComponent).inject(this)
    }

}