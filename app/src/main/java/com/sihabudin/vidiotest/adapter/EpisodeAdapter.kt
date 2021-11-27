package com.sihabudin.vidiotest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sihabudin.vidiotest.databinding.ItemEpisodeBinding
import com.sihabudin.vidiotest.model.Episode

class EpisodeAdapter(
    private val episodes: List<Episode>,
    private val onDownloadClick: (Episode) -> Unit
) : RecyclerView.Adapter<EpisodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position],onDownloadClick)
    }

    override fun getItemCount(): Int = episodes.size
}