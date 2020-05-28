package com.tost255.justone

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.firebase.ui.auth.AuthUI
import com.tost255.justone.utils.FirebaseHelper
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        approve_reg_checkbox.setOnLongClickListener { openLink() }
        google_sign_in_button.isEnabled = false
        google_sign_in_button.setOnClickListener {signIn()}
        approve_reg_checkbox.setOnClickListener {clickOnCheckbox()}
    }

    override fun onStart() {
        super.onStart()
        val animated = AnimatedVectorDrawableCompat.create(this, R.drawable.animation_start_screen)
        animated?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                start_screen_small_animation.post { animated.start() }
            }
        })
        start_screen_small_animation.setImageDrawable(animated)
        animated?.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
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
        if (approve_reg_checkbox.isChecked) {
            val fbHelper = FirebaseHelper()
            fbHelper.writeNewUserToDb(approve_reg_checkbox.isChecked)
        }
    }

    private fun clickOnCheckbox(){
        google_sign_in_button.isEnabled = approve_reg_checkbox.isChecked
    }

    private fun openLink(): Boolean{
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse("https://www.regulaminy.pl")
        startActivity(openURL)
        return true
    }

    companion object {
        private const val RC_SIGN_IN = 1
    }
}


