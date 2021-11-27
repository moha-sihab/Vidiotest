package com.sihabudin.vidiotest.model
import com.sihabudin.vidiotest.enum.DownloadStatus

data class Episode(
    val id: Long,
    val title: String,
    val duration: String,
    val thumbnailUrl: String,
    val description: String,
    val isFree: Boolean,
    var downloadStatus: DownloadStatus
)
