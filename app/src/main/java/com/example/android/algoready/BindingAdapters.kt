/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.algoready.network.VideoProperty
import com.example.android.algoready.overview.YouTubeDataApiStatus
import com.example.android.algoready.overview.PhotoGridAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

/**
 * When there is no Video property data (data is null), the [RecyclerView] is hidden,
 * otherwise it is shown.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<VideoProperty>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

/**
 * @param videoId from [VideoProperty] is used to cue in the [youTubePlayerView] and load it
 */

@BindingAdapter("videoId")
fun setVideoId(youTubePlayerView: YouTubePlayerView, videoId: String?) {
    youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            youTubePlayer.cueVideo(videoId!!, 0f)
        }
    })
}

/**
 * Used the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imgView)
    }
}

/**
 * This binding adapter displays the [YouTubeDataApiStatus] of the network request in an image view. When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: YouTubeDataApiStatus?) {
    when (status) {
        YouTubeDataApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        YouTubeDataApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        YouTubeDataApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }

        else -> {}
    }
}
