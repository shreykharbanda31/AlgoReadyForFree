/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready.detail

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.algoready.R
import com.example.android.algoready.databinding.FragmentDetailBinding


/**
 * This [Fragment] shows the detailed information about the selected YouTube Video.
 * It sets this information in the [DetailViewModel], which it gets as a Parcelable property
 * through Jetpack Navigation's SafeArgs.
 */
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the [DetailFragment]
     * to enable Data Binding to observe LiveData.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        binding = FragmentDetailBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        val marsProperty = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = DetailViewModelFactory(marsProperty, application)
        viewModel = ViewModelProvider(
                this, viewModelFactory).get(DetailViewModel::class.java)
        // Giving the binding access to the DetailViewModel built using the ViewModelFactory
        binding.viewModel = viewModel
        val youTubePlayerView = binding.youtubePlayerView
        // Allows Data Binding to Observe LiveData with the lifecycle of youTubePlayerView
        lifecycle.addObserver(youTubePlayerView)

        // Observe the navigateToSelectedProperty LiveData and perform UI changes
        binding.viewModel?.isFullScreen?.observe(this) { isFullScreen ->
            updatePlayerLayout(isFullScreen)
        }

        return binding.root
    }

    /**
     *  Calls appropriate functions to change [layoutParams] for the [youTubePlayerView]
     *  along with orientation and full screen button updates
     */

    private fun updatePlayerLayout(isFullScreen: Boolean) {
        val layoutParams = binding.youtubePlayerView.layoutParams as ConstraintLayout.LayoutParams
        if (::binding.isInitialized) {
            if (isFullScreen) {
                fullScreenLayoutActivated(layoutParams)
            } else {
                layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }

            binding.youtubePlayerView.layoutParams = layoutParams
        }
    }

    /**
     *  Changes orientation of the screen and converts the full screen button to an exit button
     */

    private fun fullScreenLayoutActivated(layoutParams: ConstraintLayout.LayoutParams) {
        // Change orientation to horizontal
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setPlayerViewSize(layoutParams)
        binding.fullscreenButton.text = getString(R.string.exit_fullscreen)
    }

    /**
     * Sets the [YoutubePlayerView]'s constraints and [layoutParams] based on screen size
     */

    private fun setPlayerViewSize(layoutParams: ConstraintLayout.LayoutParams) {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val pixelsPerInch = displayMetrics.densityDpi
        val screenWidth = displayMetrics.widthPixels - pixelsPerInch
        val screenHeight = displayMetrics.heightPixels - pixelsPerInch / 2
        layoutParams.width = screenWidth
        layoutParams.height = screenHeight
    }
}
