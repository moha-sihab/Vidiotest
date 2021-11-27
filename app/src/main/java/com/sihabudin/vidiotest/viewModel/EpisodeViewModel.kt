package com.sihabudin.vidiotest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sihabudin.vidiotest.model.Episode
import com.sihabudin.vidiotest.model.EpisodeData

class EpisodeViewModel() : ViewModel() {
    val _episode = MutableLiveData<List<Episode>>()
    fun setEpisode(episodeList:List<Episode>){
        _episode.value = episodeList
    }
    fun getEpisode(): LiveData<List<Episode>>
    {

        return  _episode
    }

}