/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready.loginregistration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class WelcomeScreenViewModel( app: Application) : AndroidViewModel(app) {

    // Internal MutableLiveData to handle navigation to the Login Screen
    private val _navigateToLogin = MutableLiveData(false)
    // External immutable LiveData to handle navigation to the Login Screen
    val navigateToLogin : LiveData<Boolean>
        get() = _navigateToLogin

    // Internal MutableLiveData to handle navigation to the Register Screen
    private val _navigateToRegister = MutableLiveData(false)
    // External immutable LiveData to handle navigation to the Register Screen
    val navigateToRegister : LiveData<Boolean>
        get() = _navigateToRegister

    /**
     * Sets the value of the [navigateToLogin] [LiveData] to true when clicked
     */
    fun loginClicked(){
        _navigateToLogin.value = true
    }
    /**
     * Sets the value of the [navigateToRegister] [LiveData] to true when clicked
     */
    fun registerClicked(){
        _navigateToRegister.value = true
    }
    /**
     * Sets the value of the [navigateToLogin] [LiveData] to false after
     * [WelcomeScreenFragment] has completed navigation
     */
    fun navigatedToLogin(){
        _navigateToLogin.value = false
    }
    /**
     * Sets the value of the [navigateToRegister] [LiveData] to false after
     * [WelcomeScreenFragment] has completed navigation
     */
    fun navigatedToRegister(){
        _navigateToRegister.value = false
    }

}