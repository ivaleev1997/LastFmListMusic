package com.education.songslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.core_api.mediator.AppWithFacade
import com.education.songslist.di.SongsListComponent
import com.education.songslist.di.ViewModelTrigger
import com.education.songslist.view.SongsListAdapter
import com.education.songslist.viewmodel.SongsListViewModel
import javax.inject.Inject

class SongsListFragment: Fragment() {
    companion object {
        fun getInstance(): SongsListFragment {
            return SongsListFragment()
        }
    }

    @Inject
    lateinit var appViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModelTrigger: ViewModelTrigger

    private var bottomSheetFragment: BottomSheetFragment? = null

    private lateinit var songsListViewModel: SongsListViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var topTrackAdapter: SongsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SongsListComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
        songsListViewModel = ViewModelProviders.of(this, appViewModelFactory).get(SongsListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_songs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.topTracksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        topTrackAdapter =
            SongsListAdapter { track ->
                if (bottomSheetFragment == null) {
                    bottomSheetFragment = BottomSheetFragment.newInstance(track)
                } else {
                    bottomSheetFragment?.updateContent(track)
                }

                bottomSheetFragment?.show(
                    requireActivity().supportFragmentManager,
                    bottomSheetFragment?.tag
                )
            }
        recyclerView.adapter = topTrackAdapter

        songsListViewModel.loadData()

        songsListViewModel.tracksLiveData.observe(viewLifecycleOwner) { t ->
            topTrackAdapter.listTopTracks = t
            topTrackAdapter.notifyDataSetChanged()
        }
    }
}