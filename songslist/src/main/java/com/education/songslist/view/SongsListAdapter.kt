package com.education.songslist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.education.core_api.dto.LastFmTrack
import com.education.songslist.R

class SongsListAdapter(
    private val onClickCallBack: (LastFmTrack) -> Unit
): RecyclerView.Adapter<SongsListAdapter.ViewHolder>() {

    var listTopTracks: List<LastFmTrack> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_top_tracks, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTopTracks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = listTopTracks[position]
        val url = song.images.first{ it.size == "medium" }.url
        Glide.with(holder.itemView).load(url).into(holder.trackImage)
        holder.trackName.text = song.name
        holder.trackArtist.text = song.artist.name
        holder.cardView.setOnClickListener {
            onClickCallBack(song)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val trackImage: ImageView = itemView.findViewById(R.id.trackImage)
        val trackName: TextView = itemView.findViewById(R.id.trackName)
        val trackArtist: TextView = itemView.findViewById(R.id.trackArtist)
    }
}