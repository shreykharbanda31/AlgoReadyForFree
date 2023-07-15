/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

/**
 * Uses the [mGoogleSignInClient] to initialize and signOut of Google Services for Sign-In
 * and Registration
 */

class GoogleSignInManager(private val application: Application) {
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    fun initialize() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(application, gso)
    }

    fun signOut(onComplete: (Boolean) -> Unit) {
        mGoogleSignInClient.signOut().addOnCompleteListener { task ->
            onComplete(task.isSuccessful)
        }
    }
}
