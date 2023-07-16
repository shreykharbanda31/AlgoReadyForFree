/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready.loginregistration

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * The [ViewModel] that is attached to the [LoginFragment]
 */

class LoginViewModel(app:Application): AndroidViewModel(app) {

    private lateinit var auth: FirebaseAuth

    // The internal MutableLiveData that tracks the status of Login button click
    private val _loginClicked = MutableLiveData(false)
    // The external LiveData that tracks the status of Login button click
    val loginClicked : LiveData<Boolean>
        get() = _loginClicked

    // The internal MutableLiveData that tracks the status of Sign In Success
    private val _signInSuccess = MutableLiveData(false)
    // The external LiveData that tracks the status of Sign In Success
    val signInSuccess: LiveData<Boolean>
        get() = _signInSuccess

    // The internal MutableLiveData that tracks the status of Sign In Failure
    private val _signInFailure = MutableLiveData(false)
    // The external LiveData that tracks the status of Sign In Failure
    val signInFailure: LiveData<Boolean>
        get() = _signInFailure

    /**
     * Sets the value of the [loginClicked] [LiveData] to true when clicked
     */
    fun manualLoginClicked(){
        _loginClicked.value = true
    }

    /**
     * Function for signing in with email and password using [Firebase] authentication.
     * It utilizes the [FirebaseAuth] instance to perform the sign-in operation. Upon completion,
     * it invokes corresponding functions in the [ViewModel] to handle the
     * success or failure of the sign-in process.
     */
    fun signInWithEmailAndPassword(activity: Activity, email: String, password: String) {
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    // Calls a function in your ViewModel to handle the successful sign-in
                    handleSignInSuccess()
                } else {
                    // Calls a function in your ViewModel to handle the sign-in failure
                    handleSignInFailure()
                }
            }
    }

    /**
     * Sets the value of the [signInFailure] and [signInSuccess] [LiveData] to true
     */

    fun handleSignInFailure() {
        _signInFailure.value = true
    }

    fun handleSignInSuccess() {
        _signInSuccess.value = true
    }

    /**
     * Sets the value of the [loginClicked] [LiveData] to false after [LoginFragment] has performed its tasks
     */
    fun loginClicked(){
        _loginClicked.value = false
    }


}