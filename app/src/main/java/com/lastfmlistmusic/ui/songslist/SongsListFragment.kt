package com.lastfmlistmusic.ui.songslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lastfmlistmusic.R
import com.lastfmlistmusic.ViewModelFactory
import com.lastfmlistmusic.ui.BottomSheetFragment
import com.lastfmlistmusic.ui.TopTrackAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SongsListFragment: DaggerFragment() {

    companion object {
        fun getInstance(): SongsListFragment {
            return SongsListFragment()
        }
    }

    @Inject
    lateinit var appViewModelFactory: ViewModelFactory

    private var bottomSheetFragment: BottomSheetFragment? = null

    private val songsListViewModel: SongsListViewModel by viewModels {
        appViewModelFactory
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var topTrackAdapter: TopTrackAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.topTracksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        topTrackAdapter = TopTrackAdapter { track ->
            if (bottomSheetFragment == null) {
                bottomSheetFragment = BottomSheetFragment.newInstance(track)
            } else {
                bottomSheetFragment?.updateContent(track)
            }

            bottomSheetFragment?.show(requireActivity().supportFragmentManager, bottomSheetFragment?.tag)
        }
        recyclerView.adapter = topTrackAdapter

        songsListViewModel.loadData()

        songsListViewModel.tracksLiveData.observe(viewLifecycleOwner) { t ->
            topTrackAdapter.listTopTracks = t
            topTrackAdapter.notifyDataSetChanged()
        }
    }
}