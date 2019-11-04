package com.lastfmlistmusic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var topTrackAdapter: TopTrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.tracksLiveData?.observe(this, Observer { t ->
            topTrackAdapter.listTopTracks = t
            topTrackAdapter.notifyDataSetChanged()
        })

        recyclerView = findViewById(R.id.topTracksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        topTrackAdapter = TopTrackAdapter()
        recyclerView.adapter = topTrackAdapter

        mainActivityViewModel.loadData()

    }
}
