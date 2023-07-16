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
import com.example.android.algoready.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * This [Fragment] shows the Registration Screen for the app.
 */
class RegisterFragment : Fragment(){
    private lateinit var auth: FirebaseAuth
    /**
     * Lazily initialize our [RegisterViewModel].
     */
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
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
            this.findNavController().navigate(RegisterFragmentDirections.actionNowToLogin())
        }
    }

    /**
     * Inflates the layout with [DataBinding], sets its lifecycle owner to the [RegisterFragment]
     * to enable Data Binding to observe [LiveData].
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        // Giving the binding access to the RegisterViewModel
        binding.viewModel = viewModel
        //Initializing Firebase authentication
        auth = Firebase.auth
        // Observe the registerClicked LiveData and perform UI changes
        viewModel.registerClicked.observe(viewLifecycleOwner) {
            if (it) {
                // Gets the input for name, email and password/confirm_password from the layout
                val name = binding.editTextFullName.text.toString()
                val email = binding.editTextTextEmailAddress.text.toString()
                val password = binding.editTextTextPassword.text.toString()
                val confirm_password = binding.editTextConfirmPassword.text.toString()

                // Performing registration validation checks
                if (name.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Please enter your full name",
                        Toast.LENGTH_LONG
                    ).show()
                    return@observe
                }
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

                if (password.length < 6) {
                    Toast.makeText(
                        requireContext(),
                        "Minimum Password Length: 6",
                        Toast.LENGTH_LONG
                    ).show()
                    return@observe
                }

                if (confirm_password.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Please re-enter your password",
                        Toast.LENGTH_LONG
                    ).show()
                    return@observe
                }

                if (password != confirm_password) {
                    Toast.makeText(
                        requireContext(),
                        "Passwords don't match",
                        Toast.LENGTH_LONG
                    ).show()
                    return@observe
                }

                // Call your ViewModel's registerWithEmailAndPassword
                viewModel.registerWithEmailAndPassword(requireActivity(), email, password)
            }
        }
        //Observes changes in LiveData to update UI accordingly
        observeViewModel()

        return binding.root
    }

    /**
     * [RegisterViewModel] observation for registration success and failure.
     * Handles navigation to the login screen upon successful registration and
     * displays appropriate toast messages for success and failure cases.
     */
    private fun observeViewModel() {
        viewModel.registerSuccess.observe(viewLifecycleOwner) {
            if (it) {
                Firebase.auth.signOut()
                Toast.makeText(requireContext(), "Registration Successful", Toast.LENGTH_LONG).show()
                this.findNavController().navigate(RegisterFragmentDirections.actionNowToLogin())
            }
            // Tell the ViewModel we've made the navigation call to prevent multiple navigation
            viewModel.registerClicked()
        }

        viewModel.registerFailure.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Registration Failed", Toast.LENGTH_LONG).show()
            }
        }
    }



}