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
 * The [ViewModel] that is attached to the [RegisterFragment]
 */
class RegisterViewModel(app:Application): AndroidViewModel(app) {
    private lateinit var auth: FirebaseAuth

    // The internal MutableLiveData that tracks the status of Register button click
    private val _registerClicked = MutableLiveData(false)
    // The external LiveData that tracks the status of Register button click
    val registerClicked : LiveData<Boolean>
        get() = _registerClicked

    // The internal MutableLiveData that tracks the status of Register Success
    private val _registerSuccess = MutableLiveData<Boolean>()
    // The external LiveData that tracks the status of Register Success
    val registerSuccess: LiveData<Boolean>
        get() = _registerSuccess

    // The internal MutableLiveData that tracks the status of Register Failure
    private val _registerFailure = MutableLiveData<Boolean>()
    // The external LiveData that tracks the status of Register Failure
    val registerFailure: LiveData<Boolean>
        get() = _registerFailure

    /**
     * Sets the value of the [registerClicked] [LiveData] to true when clicked
     */
    fun manualRegisterClicked(){
        _registerClicked.value = true
    }

    /**
     * Function for registering with email and password using [Firebase] authentication.
     * It utilizes the [FirebaseAuth] instance to perform the register operation. Upon completion,
     * it invokes corresponding functions in the [ViewModel] to handle the
     * success or failure of the registration process.
     */
    fun registerWithEmailAndPassword(activity: Activity, email: String, password: String) {
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Calls ViewModel function to handle the successful registration
                    handleRegisterSuccess()
                } else {
                    // Calls ViewModel function to handle the registration failure
                    handleRegisterFailure()
                }
            }
    }

    /**
     * Sets the value of the [registerFailure] and [registerSuccess] [LiveData] to true
     */
    fun handleRegisterFailure() {
        _registerFailure.value = true
    }

    fun handleRegisterSuccess() {
        _registerSuccess.value = true
    }

    /**
     * Sets the value of the [registerClicked] [LiveData] to false after [RegisterFragment] has performed its tasks
     */
    fun registerClicked(){
        _registerClicked.value = false
    }


}