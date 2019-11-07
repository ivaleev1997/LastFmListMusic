package com.lastfmlistmusic.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lastfmlistmusic.R
import com.lastfmlistmusic.data.remote.model.LastFmTrack
import com.lastfmlistmusic.databinding.ItemTopTracksBinding

class TopTrackAdapter(val iCardViewCallBack: ICardViewCallBack): RecyclerView.Adapter<TopTrackAdapter.ViewHolder>() {

    var listTopTracks: List<LastFmTrack>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val itemTopTracksBinding = DataBindingUtil.inflate<ItemTopTracksBinding>(layoutInflater,
            R.layout.item_top_tracks,parent,false)

        return ViewHolder(itemTopTracksBinding)
    }

    override fun getItemCount(): Int {
        return listTopTracks?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listTopTracks?.get(position) != null) {
            holder.itemTopTracksBinding.track = listTopTracks?.get(position)
            holder.itemTopTracksBinding.cardView.setOnClickListener{ iCardViewCallBack.onCardViewClicked(listTopTracks!!.get(position)) }
        }

    }


    class ViewHolder(val itemTopTracksBinding: ItemTopTracksBinding): RecyclerView.ViewHolder(itemTopTracksBinding.root)

    interface ICardViewCallBack {
        fun onCardViewClicked(track: LastFmTrack)
    }
}