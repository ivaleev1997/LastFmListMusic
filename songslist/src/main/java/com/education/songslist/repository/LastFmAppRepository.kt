package com.education.songslist.repository

import com.education.core_api.dto.LastFmTrack

interface LastFmAppRepository {
    suspend fun loadMusic(): List<LastFmTrack>?
}