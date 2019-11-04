package com.lastfmlistmusic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lastfmlistmusic.data.LastFmTrack
import kotlinx.coroutines.*

class MainActivityViewModel: ViewModel() {
    private val repository = LastFmAppRepository(LastFmApiSingleton().getInstance())
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val tracksLiveData: MutableLiveData<List<LastFmTrack>>? = MutableLiveData()


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun loadData() {
        uiScope.launch {
            val job = withContext(Dispatchers.IO) {
                repository.loadMusic()
            }
            tracksLiveData?.postValue(job)
        }
    }
}