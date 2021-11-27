package com.sihabudin.vidiotest.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sihabudin.vidiotest.R
import com.sihabudin.vidiotest.databinding.ItemEpisodeBinding
import com.sihabudin.vidiotest.enum.DownloadStatus
import com.sihabudin.vidiotest.model.Episode

class EpisodeViewHolder(
    val binding: ItemEpisodeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(episode: Episode, onDownloadClick: (Episode) -> Unit) {
        binding.titleView.text = episode.title
        binding.descriptionView.text = episode.description
        binding.durationView.text = episode.duration
        binding.progressView.isVisible = (episode.downloadStatus == DownloadStatus.DOWNLOADING)
        binding.downloadButton.isVisible =
            (episode.downloadStatus !== DownloadStatus.DOWNLOAD_FINISH)
        binding.downloadButton.setOnClickListener {
            onDownloadClick(episode)
            binding.progressView.isVisible = (episode.downloadStatus == DownloadStatus.DOWNLOADING)

            /***
             * TODO :
             * 1. call vidioSDK : VidioSDK.downloadEpisode(episodeId: Long) in another thread so it won't block UI
             * 2. when download in progress set `binding.progressView.isVisible = true`
             * 3. when download finish set `binding.downloadButton.isVisible = false` `binding.progressView.isVisible = false`
             *
             * Notes :
             * - Be carefull on accessing view in another thread, since recycleview can be recycled
             */
        }


        Glide.with(binding.root.context)
            .load(R.drawable.ppt)
            .into(binding.lytImage)
    }

}