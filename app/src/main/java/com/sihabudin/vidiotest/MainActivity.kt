package com.sihabudin.vidiotest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkManager.*
import com.sihabudin.vidiotest.adapter.EpisodeAdapter
import com.sihabudin.vidiotest.databinding.ActivityMainBinding
import com.sihabudin.vidiotest.enum.DownloadStatus
import com.sihabudin.vidiotest.model.Episode
import com.sihabudin.vidiotest.model.EpisodeData
import com.sihabudin.vidiotest.viewModel.EpisodeViewModel
import com.sihabudin.vidiotest.workmanager.DownloadWorker

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: EpisodeViewModel
//    private var listEpisode: ArrayList<Episode> = ArrayList(0)
    private var defaultListEpisode:  ArrayList<Episode> = ArrayList(0)
    private var episodeAdapter = EpisodeAdapter(defaultListEpisode,{episode -> onDownload(episode)})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(EpisodeViewModel::class.java)
        defaultListEpisode = EpisodeData().getEpisode()
        viewModel.setEpisode(defaultListEpisode)
        viewModel.getEpisode().observe(this, Observer {
            showEpisodeList(it)
        })



    }

    private fun showEpisodeList(listEpisodes : List<Episode>){
        episodeAdapter = EpisodeAdapter(listEpisodes,{episode -> onDownload(episode)})
        with(binding.rvEpisode) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = episodeAdapter
        }
    }

    private fun onDownload(episode:Episode){
        val workManager = WorkManager.getInstance(binding.root.context)

        defaultListEpisode ?.find { it.id == episode.id} ?.downloadStatus= DownloadStatus.DOWNLOADING
        viewModel.setEpisode(defaultListEpisode)

        val downloadWork = OneTimeWorkRequestBuilder<DownloadWorker>().build()
        var continuation= workManager.beginUniqueWork(
            episode.id.toString(),
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequest.from(DownloadWorker::class.java))

        continuation = continuation.then(downloadWork)
        continuation.enqueue()

        Log.e("mulai download id=",downloadWork.id.toString())

        workManager.getWorkInfoByIdLiveData(downloadWork.id)
            .observe(this, Observer { workInfo ->
                if (workInfo != null && workInfo.state.isFinished) {

                    Log.e("berhasil download id=",workInfo.id.toString())

                        defaultListEpisode?.find { it.id == episode.id }?.downloadStatus =
                            DownloadStatus.DOWNLOAD_FINISH
                        viewModel.setEpisode(defaultListEpisode)
                        viewModel.getEpisode().observe(this, Observer {
                            showEpisodeList(it)
                        })

                }
            })


    }
}