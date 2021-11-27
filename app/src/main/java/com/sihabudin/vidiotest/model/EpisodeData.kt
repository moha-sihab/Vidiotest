package com.sihabudin.vidiotest.model

import com.sihabudin.vidiotest.enum.DownloadStatus

class EpisodeData {

    fun getEpisode() : ArrayList<Episode>{

        var episodeList : ArrayList<Episode> = ArrayList(0)

        for(i in 1..8){

            episodeList.add(
                Episode(
                    i.toLong(),
                    "PPT Episode $i",
                    "50m",
                    "",
                    "Para Pencari Tuhan Jilid 14 Episode 1 bercerita tentang....",
                    true,
                     DownloadStatus.NOT_DOWNLOADED


                )

            )
        }




        return episodeList
    }

}