package com.lastfmlistmusic

import android.app.Application
import com.education.core.CoreProviderFactory
import com.education.core_api.mediator.NetworkProvider
import com.education.core_api.mediator.ProvidersFacade
import dagger.Component

@Component(
    dependencies = [NetworkProvider::class],
    modules = [MediatorBindings::class]
)
interface FacadeComponent: ProvidersFacade {
    companion object {
        fun init(application: Application): FacadeComponent {
            return DaggerFacadeComponent.builder()
                .networkProvider(CoreProviderFactory.createNetworkBuilder())
                .build()
        }
    }

    fun inject(app: App)
}