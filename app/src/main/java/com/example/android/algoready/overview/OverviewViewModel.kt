/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready.overview

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.algoready.network.VideoProperty
import androidx.lifecycle.viewModelScope
import com.example.android.algoready.network.YouTubeApi
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

enum class YouTubeDataApiStatus { LOADING, ERROR, DONE }
/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel() : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<YouTubeDataApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<YouTubeDataApiStatus>
        get() = _status

    // Internal MutableLiveData for updating the List of VideoProperty with new values
    private val _properties = MutableLiveData<List<VideoProperty>>()

    // The external immutable LiveData for the list of properties
    val properties: LiveData<List<VideoProperty>>
        get() = _properties

    // Internal MutableLiveData to handle navigation to the selected property
    private val _navigateToSelectedProperty = MutableLiveData<VideoProperty>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedProperty: LiveData<VideoProperty>
        get() = _navigateToSelectedProperty


    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getYouTubeDataProperties()
    }

    /**
     * Gets YouTube data property information from the Mars API Retrofit service and
     * updates the [VideoProperty] [List] and [YouTubeDataApiStatus] [LiveData]. The Retrofit service
     * returns a coroutine Deferred, which we await to get the result of the transaction.
     */
     private fun getYouTubeDataProperties() {
        viewModelScope.launch {
            _status.value = YouTubeDataApiStatus.LOADING
            try {
                _properties.value = YouTubeApi.retrofitService.getProperties()
                _status.value = YouTubeDataApiStatus.DONE
            } catch (e: Exception) {
                _status.value = YouTubeDataApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    /**
     * When the property is clicked, the [_navigateToSelectedProperty] [MutableLiveData] is set
     * @param videoProperty The [VideoProperty] that was clicked on.
     */
    fun displayPropertyDetails(videoProperty: VideoProperty) {
        _navigateToSelectedProperty.value = videoProperty
    }

    /**
     * After the navigation has taken place, navigateToSelectedProperty is set to null
     */
    @SuppressLint("NullSafeMutableLiveData")
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    /**
     * Signs out of the app using [Firebase] and [auth]
     */

    fun logOut(){
        Firebase.auth.signOut()
    }

}
