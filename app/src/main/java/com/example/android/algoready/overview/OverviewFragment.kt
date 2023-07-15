/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready.overview

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.algoready.R
import com.example.android.algoready.databinding.FragmentOverviewBinding

/**
 * This fragment shows the the status of the [YouTubeDataApi] web services transaction.
 */

class OverviewFragment : Fragment() {
    /**
     * Lazily initialize the [OverviewViewModel].
     */
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView with clickHandler lambda that
        // tells the viewModel when our property is clicked
        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        // Observe the navigateToSelectedProperty LiveData and Navigate when it isn't null
        // After navigating, displayPropertyDetailsComplete() is called so that the ViewModel is ready
        // for another navigation event.
        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(OverviewFragmentDirections.actionShowDetail(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayPropertyDetailsComplete()
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    /**
     * Handles [OnBackPressedCallback] and calls [OverviewViewModel]'s logout() method
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.logOut()
                    findNavController().apply {
                        popBackStack(
                            R.id.welcomeScreenFragment,  // ID of the destination to retain
                            false // false to include the destination with the given ID in the back stack
                        )
                    }

                }
            }
            )
    }

    /**
     * Inflates the overflow menu
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Performs navigation after finding NavController() and
     * calls the [OverviewViewModel] logOut() method
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_logout -> {
                viewModel.logOut()
                findNavController().apply {
                    popBackStack(
                        R.id.welcomeScreenFragment,  // ID of the destination you want to retain
                        false // false to include the destination with the given ID in the back stack
                    )
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

