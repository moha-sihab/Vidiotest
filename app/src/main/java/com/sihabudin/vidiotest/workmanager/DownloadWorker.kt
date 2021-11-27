package com.sihabudin.vidiotest.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.sihabudin.vidiotest.enum.DownloadStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DownloadWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    val PROGRESS = "PROGRESS"
    override fun doWork(): Result {
        downloadEpisode()
       // val output: Data = workDataOf(DownloadStatus.DOWNLOADING to DownloadStatus.DOWNLOAD_FINISH)
        return Result.success()

    }

    private fun downloadEpisode() {
        runBlocking {
            launch {
                delay(2000L)
                for (x in 1..100) {
                    Log.e("download process=", x.toString())
                }
            }

            delay(4000L)

            Log.e("download process=", "selesai")
        }
    }
}