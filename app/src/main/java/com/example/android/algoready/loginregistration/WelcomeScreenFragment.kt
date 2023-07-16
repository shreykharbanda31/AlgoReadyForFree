/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready.loginregistration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.algoready.databinding.FragmentWelcomescreenBinding

/**
 * This [Fragment] shows the Welcome Screen for the app.
 */
class WelcomeScreenFragment : Fragment(){

    /**
     * Lazily initialize our [WelcomeScreenViewModel].
     */
    private val viewModel: WelcomeScreenViewModel by lazy {
        ViewModelProvider(this).get(WelcomeScreenViewModel::class.java)
    }

    /**
     * Inflates the layout with [DataBinding], sets its lifecycle owner to the [WelcomeScreenFragment]
     * to enable Data Binding to observe [LiveData].
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application

        val binding = FragmentWelcomescreenBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        // Giving the binding access to the WelcomeScreenViewModel
        binding.viewModel = viewModel

        // Observe the navigateToLogin LiveData and perform UI changes
        viewModel.navigateToLogin.observe(viewLifecycleOwner, Observer {
            if ( it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(WelcomeScreenFragmentDirections.actionLogin())
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.navigatedToLogin()
            }
        })

        // Observe the navigateToRegister LiveData and perform UI changes
        viewModel.navigateToRegister.observe(viewLifecycleOwner, Observer {
            if ( it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(WelcomeScreenFragmentDirections.actionRegister())
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.navigatedToRegister()
            }
        })
        return binding.root
    }
}