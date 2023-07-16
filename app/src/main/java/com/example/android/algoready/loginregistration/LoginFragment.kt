/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready.loginregistration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.algoready.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * This [Fragment] shows the Login Screen for the app.
 */
class LoginFragment : Fragment(){
    private lateinit var auth: FirebaseAuth
    /**
     * Lazily initialize our [LoginViewModel].
     */
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    /**
     * This function checks if the user is signed in using [Firebase] [auth]
     * and updates the UI accordingly by navigating to the overview screen if the user is signed in.
     */

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            this.findNavController().navigate(LoginFragmentDirections.actionShowOverview())
        }
    }
    /**
     * Inflates the layout with [DataBinding], sets its lifecycle owner to the [LoginFragment]
     * to enable Data Binding to observe [LiveData].
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        // Giving the binding access to the LoginViewModel
        binding.viewModel = viewModel
        //Initializing Firebase authentication
        auth = Firebase.auth
        // Observe the loginClicked LiveData and perform UI changes
        viewModel.loginClicked.observe(viewLifecycleOwner) {
            if (it) {

                // Gets the input for email and password from the layout
                val email = binding.editTextTextEmailAddress.text.toString()
                val password = binding.editTextTextPassword.text.toString()

                // Performing login validation checks
                if (email.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Please enter your email address",
                        Toast.LENGTH_LONG
                    ).show()
                    return@observe
                }

                if (password.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Please enter your password",
                        Toast.LENGTH_LONG
                    ).show()
                    return@observe
                }

                // Calling LoginViewModel's signInWithEmailAndPassword function
                viewModel.signInWithEmailAndPassword(requireActivity(), email, password)
            }
        }
        //Observes changes in LiveData to update UI accordingly
        observeViewModel()

        return binding.root
    }

    /**
     * [LoginViewModel] observation for sign-in success and failure.
     * Handles navigation to the overview screen upon successful sign-in and
     * displays appropriate toast messages for success and failure cases.
     */
    private fun observeViewModel() {
        viewModel.signInSuccess.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Sign-in Successful", Toast.LENGTH_LONG).show()
                this.findNavController().navigate(LoginFragmentDirections.actionShowOverview())
            }
            // Tell the ViewModel we've made the navigation call to prevent multiple navigation
            viewModel.loginClicked()
        }

        viewModel.signInFailure.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Sign-in Failed", Toast.LENGTH_LONG).show()
            }
        }
    }



}