package com.education.songslist

import androidx.fragment.app.FragmentManager
import com.education.core_api.mediator.SongsListMediator
import javax.inject.Inject

class SongsListMediatorImpl
    @Inject constructor(): SongsListMediator {
    override fun startSongsListFragment(containerId: Int, fragmentManager: FragmentManager) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, SongsListFragment.getInstance())
        transaction.commit()
    }
}