/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready.network

import android.os.Parcelable
import androidx.lifecycle.LiveData
import com.example.android.algoready.overview.YouTubeDataApiStatus
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Gets Video property information from the YouTubeData API Retrofit service and updates the
 * [VideoProperty] and [YouTubeDataApiStatus] [LiveData]. The Retrofit service returns a coroutine
 * Deferred, which we await to get the result of the transaction.
 */

@Parcelize
data class VideoProperty(
        val videoName : String,
val videoId: String,
        // used to map img_src from the JSON to imgSrcUrl
        @Json(name = "videoImg") val imgSrcUrl: String,
) : Parcelable {}
