package com.tost255.justone

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.database.*
import com.tost255.justone.utils.FirebaseHelper
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        google_sign_in_button.setOnClickListener {signIn()}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                checkFirstSignIn()
                val intentMainActivity = Intent(this, MainActivity::class.java)
                startActivity(intentMainActivity)
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                Toast.makeText(this, R.string.problem_sign_in_toast, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun signIn(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build())

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)
    }

    private fun checkFirstSignIn(){
        val fbHelper = FirebaseHelper()
        fbHelper.writeNewUsertoDb()
    }

    companion object {
        private const val RC_SIGN_IN = 1
    }
}


