/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.algoready.network.VideoProperty

/**
 *  The [ViewModel] associated with the [DetailFragment], containing information about the selected
 *  [VideoProperty].
 */
class DetailViewModel(videoProperty: VideoProperty, app: Application) : AndroidViewModel(app) {

    // The internal MutableLiveData that tracks the selected property
    private val _selectedProperty = MutableLiveData<VideoProperty>()

    // The external LiveData that tracks the selected property
    val selectedProperty: LiveData<VideoProperty>
        get() = _selectedProperty

    // The internal MutableLiveData that tracks the status of FullScreen button click
    private val _isFullScreen = MutableLiveData(false)
    // The external LiveData that tracks the status of FullScreen button click
    val isFullScreen: LiveData<Boolean>
        get() = _isFullScreen

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = videoProperty
    }

}

