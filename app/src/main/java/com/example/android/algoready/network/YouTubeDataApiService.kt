/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://apialgoready-server.netlify.app/.netlify/functions/api/"

/**
 * Building the Moshi object that Retrofit will be using, added the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

/**
 * Uses the Retrofit builder to build a retrofit object using a Moshi converter with the Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface YouTubeDataApiService {
    /**
     * Returns a Coroutine [List] of [VideoProperty]
     * The @GET annotation indicates that the "youtubedata" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("youtubedata")
    suspend fun getProperties(): List<VideoProperty>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object YouTubeApi {
    val retrofitService : YouTubeDataApiService by lazy { retrofit.create(YouTubeDataApiService::class.java) }
}
