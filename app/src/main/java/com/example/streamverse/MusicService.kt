package com.example.streamverse
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder

class MusicService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val musicUrl = "https://www.bensound.com/bensound-music/bensound-classical.mp3"
        mediaPlayer = MediaPlayer().apply {
            setDataSource(applicationContext, Uri.parse(musicUrl))
            isLooping = true
            prepareAsync()
            setOnPreparedListener {
                start()
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
