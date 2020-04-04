package com.lastfmlistmusic.ui

import android.os.Bundle
import com.lastfmlistmusic.R
import com.lastfmlistmusic.ui.songslist.SongsListFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startMainFragment()
    }

    private fun startMainFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, SongsListFragment.getInstance())
        fragmentTransaction.commit()
    }
}
