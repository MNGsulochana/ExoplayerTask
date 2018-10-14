package com.hackathlon.exoplayertask.ui.player

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.DefaultTimeBar
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.hackathlon.exoplayertask.R
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.base.BaseActivity
import com.hackathlon.exoplayertask.injection.component.ActivityComponent
import com.hackathlon.exoplayertask.ui.player.mvp.PlayerContract
import com.hackathlon.exoplayertask.ui.player.playlistfragment.PlayListRowFragment
import java.util.concurrent.TimeUnit


class PlayerActivity : BaseActivity<PlayerContract.Presenter>(), PlayerContract.View,
        View.OnClickListener, PlayListRowFragment.updateThePlaylist,
        PlayerControlView.VisibilityListener {


    companion object {
        const val ITEM_ID = "item_id"
    }

    private val BANDWIDTH_METER = DefaultBandwidthMeter()
    var startAutoPlay = true
    var playerview: PlayerView? = null
    var player: SimpleExoPlayer? = null
    private var lastSeenTrackGroupArray: TrackGroupArray? = null
    private var trackSelector: DefaultTrackSelector? = null
    var dataSourceFactory: DataSource.Factory? = null
    private var playWhenReady: Boolean = false
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0
    var mediaSource: MediaSource? = null

    var title_text: TextView? = null
    var backpress_image: ImageView? = null
    var playlist_image: ImageView? = null
    var playerlayout: FrameLayout? = null
    var playlistFragment: FrameLayout? = null
    var progressBar: ProgressBar? = null
    var timeBar: DefaultTimeBar? = null
    var playlistFragmentText: TextView? = null

    var playerDuration: Int? = null


    var playerlistData: MutableList<DataModel>? = null
    var playListRowFragment: PlayListRowFragment? = null


    private var currentItemIndex = 0
    private var currentItem: DataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val itemId = intent.getStringExtra(ITEM_ID)

        currentItem = presenter.getData(itemId)

        playerlistData = presenter.getDataList().toMutableList()

        playerlistData?.remove(currentItem)
        playerlistData?.add(0, currentItem!!)

        presenter.attach(this)


    }


    private fun initializePlayer() {

        // title = mSelectedMovie?.title
        dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "ExoplayerTask"))
        playerview = findViewById<View>(R.id.player_view) as PlayerView?

        title_text = findViewById(R.id.movie_title)
        backpress_image = findViewById(R.id.backward)
        playlist_image = findViewById(R.id.playall)
        playerlayout = findViewById(R.id.frame_play)
        playlistFragment = findViewById(R.id.playlist_fragment)
        playlistFragmentText = findViewById(R.id.playlist_fragmenttext)
        progressBar = findViewById(R.id.progress_bar)
        title_text?.text = currentItem?.title


        backpress_image?.setOnClickListener(this)
        playlist_image?.setOnClickListener(this)

        playerview?.requestFocus()

        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(BANDWIDTH_METER)

        trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        lastSeenTrackGroupArray = null

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)

        playerview?.player = player


        player?.playWhenReady = startAutoPlay

        player?.addListener(PlayerEventListener())
        mediaSource = ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(currentItem?.videourl))

        Log.d("seektimeplayer",""+presenter.getSeekTime(currentItem?.id!!))
        player?.seekTo(presenter.getSeekTime(currentItem?.id!!))
        player?.prepare(mediaSource, false, false)

        playerview?.setControllerVisibilityListener(this)

    }

    override fun contentFocusedClicked(data: DataModel, clicked: Boolean?) {

        if (clicked!!) {
            currentItemIndex = 0
            presenter.saveSeekTime(currentItem?.id!!, player?.currentPosition ?: 0)
            currentItem = data
            releasePlayer()
            initializePlayer()
        }
        // removePlaylist()


    }



    override fun onVisibilityChange(visibility: Int) {
        // removePlaylist()

    }





    /* private fun removeInstances(close: Boolean?) {
         if (player != null) {
             playerCurrentPos = player?.getCurrentPosition()!!.toInt()
             playerDuration = player?.getDuration()!!.toInt()
         }
         releasePlayer()
         if (close!!) finish()
     }*/


    /*  override fun updatePlaylist() {

      }*/

    /* private fun removePlaylist() {
         if (playListRowFragment != null) {
             timeBar?.setFocusable(true)
             playlistFragment?.setFocusable(false)
             playlistFragment?.setVisibility(View.GONE)
             playlistFragmentText?.setVisibility(View.GONE)
             playlistFragmentText?.setVisibility(View.GONE)
             playerlayout?.setVisibility(View.VISIBLE)
             fragmentManager.beginTransaction().remove(playListRowFragment).commitAllowingStateLoss()
             playListRowFragment = null
         }

     }
 */

    override fun onClick(v: View?) {

        when (v?.getId()) {
            R.id.backward -> {
                onBackPressed()
                // releasePlayer()
            }
            R.id.playall -> {
                Log.d("playlist", "clicked")

                //loadPlaylist()
                // removePlaylist()
                loadPlaylistFragment()


            }

        }
    }

    private fun loadPlaylistFragment() {
        if (playerlistData != null) {
            playListRowFragment = PlayListRowFragment().newInstance((playerlistData as java.util.ArrayList<DataModel>?)!!)
            if (playListRowFragment != null) {
                playerlayout?.setVisibility(View.GONE)
                playlistFragment?.setVisibility(View.VISIBLE)
                timeBar?.setFocusable(false)
                playlistFragment?.setFocusable(true)
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.playlist_fragment, playListRowFragment)
                        .commitAllowingStateLoss()
            }
        } else {
            playlistFragmentText?.setVisibility(View.VISIBLE)
        }
    }

    /*  private fun loadPlaylist() {
          for (i in playerlistData?.indices!!) {
              val url = playerlistData?.get(i)?.videourl
              Log.d("geturls", url)
              video_urllist?.add(url!!)
          }

          uris = video_urllist?.size as Array<Uri>
          for (i in video_urllist.indices) {
              uris!![i] = Uri.parse(video_urllist[i])
          }

          val mediaSources = arrayOfNulls<MediaSource>(uris?.size!!)
          for (i in uris?.indices!!) {
              mediaSources[i] = uris!![i] as MediaSource
          }
          mediaSource = if (mediaSources.size == 1) mediaSources[0] else ConcatenatingMediaSource(*mediaSources)
      }*/

    override fun inject(component: ActivityComponent) {
        component.inject(this)
    }

    private fun updateStartPosition() {
        playbackPosition = player?.getCurrentPosition()!!
        playerDuration = player?.getDuration()!!.toInt()
        Log.d("playbackduration", "" + playerDuration)
        Log.d("playbackpostion", "" + playbackPosition)
        currentWindow = player?.getCurrentWindowIndex()!!
        playWhenReady = player?.getPlayWhenReady()!!


    }

    private fun releasePlayer() {
        if (player != null) {
            Log.d("playwnull","playernotnull")
            updateStartPosition()
            startAutoPlay = player?.getPlayWhenReady()!!
            player?.release()
            player = null
            trackSelector = null
        }
        else{
            Log.d("playwnull","playernull")
        }
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT > 23 || player == null) {
            initializePlayer()
        }

    }


    override fun onPause() {
        super.onPause()
        if ( Util.SDK_INT>=23) {
            Log.d("onpause","onpuse")
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if ( Util.SDK_INT>=23) {
            releasePlayer()
        }
    }

    override fun onBackPressed() {
        Log.d("buffered_position","${player?.bufferedPosition}.........${player?.bufferedPercentage}")
        if (currentItem != null && player != null)
            presenter.saveSeekTime(currentItem?.id!!, player!!.currentPosition)
            presenter.savePlayedPercentage(currentItem?.id !!,player!!.bufferedPercentage)
        super.onBackPressed()
    }


    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {

        when(event?.keyCode)
        {
            KeyEvent.KEYCODE_DPAD_LEFT
                    ->
            {
                //ToDo for rewind


                //player.seekTo(player.getCurrentPosition() - 20000);

               // onBackPressed()
            }
            KeyEvent.KEYCODE_DPAD_RIGHT
                    ->
                    {
                        //ToDo for fast forward
                        val TEN_SECONDS = TimeUnit.SECONDS.toMillis(10)
                        var newPosition = player?.getCurrentPosition()!! - TEN_SECONDS
                        newPosition = if (newPosition < 0) 0 else newPosition
                        player?.seekTo(newPosition)
                        // player.seekTo(player.getCurrentPosition() + 20000);
                       // loadPlaylistFragment()
                    }
            KeyEvent.KEYCODE_BACK
                    ->
            {
                onBackPressed()
            }
        }
        return super.dispatchKeyEvent(event)
    }
    private inner class PlayerEventListener : Player.DefaultEventListener() {


        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            when (playbackState) {
                Player.STATE_IDLE       // The player does not have any media to play yet.
                -> progressBar!!.visibility = View.VISIBLE
                Player.STATE_BUFFERING  // The player is buffering (loading the content)
                -> progressBar!!.visibility = View.VISIBLE
                Player.STATE_READY      // The player is able to immediately play
                -> {
                    progressBar!!.visibility = View.GONE
                }
                Player.STATE_ENDED      // The player has finished playing the media
                -> {
                    progressBar!!.visibility = View.GONE

                    currentItemIndex++
                    if (playerlistData?.size ?: 0 > currentItemIndex) {
                        currentItem = playerlistData?.get(currentItemIndex)
                        releasePlayer()
                        initializePlayer()
                    } else finish()

                }

            }
            //updateButtonVisibilities()
        }
    }
}
