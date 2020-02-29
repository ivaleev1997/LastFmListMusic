package com.education.core_api.mediator

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface SongsListMediator {
    fun startSongsListFragment(@IdRes containerId: Int, fragmentManager: FragmentManager)
}