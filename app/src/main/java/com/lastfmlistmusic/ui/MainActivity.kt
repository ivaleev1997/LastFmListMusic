package com.lastfmlistmusic.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lastfmlistmusic.R
import com.lastfmlistmusic.data.remote.model.LastFmTrack

class MainActivity : AppCompatActivity(), TopTrackAdapter.ICardViewCallBack {

    private var bottomSheetFragment: BottomSheetFragment? = null

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var topTrackAdapter: TopTrackAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.tracksLiveData?.observe(this, Observer { t ->
            topTrackAdapter.listTopTracks = t
            topTrackAdapter.notifyDataSetChanged()
        })

        recyclerView = findViewById(R.id.topTracksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        topTrackAdapter = TopTrackAdapter(this)
        recyclerView.adapter = topTrackAdapter

        mainActivityViewModel.loadData()

    }

    override fun onCardViewClicked(track: LastFmTrack) {
        //start modal bottom fragment
        if (bottomSheetFragment == null) {
            bottomSheetFragment = BottomSheetFragment.newInstance(track)
        } else {
            bottomSheetFragment?.updateContent(track)
        }

        bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment?.tag)
    }
}
