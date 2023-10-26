package com.example.exoplayer

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log

import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity

import com.example.exoplayer.at.huber.youtubeExtractor.VideoMeta
import com.example.exoplayer.at.huber.youtubeExtractor.YouTubeExtractor
import com.example.exoplayer.at.huber.youtubeExtractor.YtFile

import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView


@Suppress("DEPRECATION")
class YoutubeExoActivity : AppCompatActivity() {
    private lateinit var playerView: PlayerView
    private lateinit var player: SimpleExoPlayer


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_exo)


        player = SimpleExoPlayer.Builder(this).build()
        playerView = findViewById(R.id.playerView)
        playerView.player = player
        player.addListener(applicationContext)
        getYouTubeVideoUrl(videoId = String())
    }

    private fun extractVideoId(youtubeUrl: String): String {
        val videoId = youtubeUrl.split("v=")[1]
        val ampersandPosition = videoId.indexOf("&")
        return if (ampersandPosition != -1) {
            videoId.substring(0, ampersandPosition)
        } else {
            videoId
        }
    }

    private fun getYouTubeVideoUrl(videoId: String) {
        val youtubeLink ="https://www.youtube.com/watch?v=icmQOZp4p6I"

   object:YouTubeExtractor(this) {
            @SuppressLint("StaticFieldLeak")
            override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta?) {
                if (ytFiles != null) {
                    val itag = 18
                    var downloadUrl: String = ytFiles[itag].getUrl()
                    Log.d("Link",downloadUrl)
                    //val mediaItem = MediaItem.fromUri(downloadUrl)
                   // player.addMediaItem(MediaItem.fromUri(downloadUrl))
                    Log.d("player", player.toString())
                    playVideo(downloadUrl)

                }
            }
        }.extract(youtubeLink)
    }

    private fun playVideo(videoUrl: String) {
        val mediaItem = MediaItem.fromUri(videoUrl)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}

private fun Any.addListener(context: Context) {


}
