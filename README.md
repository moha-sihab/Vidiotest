# Prototype Penggunaan Work Manager untuk proses download video secara asynchronous

Untuk solusi agar proses download secara asynchronous bisa menggunakan Work Manager.
Beberapa kelebihan Work Manager :

-Mendukung One-Time Task maupun Periodic Task.

-Bisa membuat chain (proses berkelanjutan) antar task.

-Mendukung sampai API Level 14.

-Proses bisa disesuaikan seperti hanya berjalan ketika ada koneksi internet atau dalam keadaan charging

Kode di bawah ini adalah prototype, sebuah skenario menggunakan Work Manager untuk proses download.

Pada kondisi yang seharusnya (production), Fungsi VidioSDK.downloadEpisode(episodeId: Long) bisa dipanggil di class DownloadWorker dan proses download akan berlangsung asynchronous.
Setelah download selesai, kode episode bisa disimpan di database room untuk membantu memberikan kondisi episode mana yang sudah di download atau belum.


![sc1](https://github.com/moha-sihab/Vidiotest/blob/master/app/src/main/res/drawable/sc1.jpg?raw=true)

![sc2](https://github.com/moha-sihab/Vidiotest/blob/master/app/src/main/res/drawable/sc2.jpg?raw=true)

![sc3](https://github.com/moha-sihab/Vidiotest/blob/master/app/src/main/res/drawable/sc3.jpg?raw=true)

![sc4](https://github.com/moha-sihab/Vidiotest/blob/master/app/src/main/res/drawable/sc4.jpg?raw=true)

![sc5](https://github.com/moha-sihab/Vidiotest/blob/master/app/src/main/res/drawable/sc5.jpg?raw=true)


