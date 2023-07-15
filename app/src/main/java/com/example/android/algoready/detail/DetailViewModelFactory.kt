/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.algoready.network.VideoProperty

/**
 * Simple ViewModel factory that provides the VideoProperty and context to the ViewModel.
 */
class DetailViewModelFactory(
    private val videoProperty: VideoProperty,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(videoProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
