package com.lastfmlistmusic.ui.songslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lastfmlistmusic.data.LastFmAppRepository
import com.lastfmlistmusic.data.remote.model.LastFmTrack
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongsListViewModel
    @Inject constructor(
    private val repository: LastFmAppRepository
): ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val tracksLiveData: LiveData<List<LastFmTrack>>
        get() = _tracksLiveData
    private val _tracksLiveData = MutableLiveData<List<LastFmTrack>>()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun loadData() {
        uiScope.launch {
            val job = withContext(Dispatchers.IO) {
                repository.loadMusic()
            }
            _tracksLiveData.value = job
        }
    }
}