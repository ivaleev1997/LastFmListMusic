package com.education.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.education.core_api.mediator.AppWithFacade
import com.education.core_api.mediator.SongsListMediator
import com.education.main.di.MainComponent
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    @Inject
    lateinit var songsListMediator: SongsListMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((application as AppWithFacade).getFacade()).inject(this)
        setContentView(R.layout.activity_main)

        startMainFragment()
    }

    private fun startMainFragment() {
        songsListMediator.startSongsListFragment(R.id.fragment_container, supportFragmentManager)
    }
}