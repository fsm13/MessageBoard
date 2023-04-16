package my.newapp.fsm.messageboard.accountHelper

import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import my.newapp.fsm.messageboard.MainActivity
import my.newapp.fsm.messageboard.R

class AccountHelper(act: MainActivity) {
    private val activity = act
    fun signUpWithEmail(email: String, password: String) {
        if(email.isNotEmpty() && password.isNotEmpty()) {
            activity.myAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    task ->
                if (task.isSuccessful) {
                    sendEmailVerification(task.result?.user!!)
                    activity.uiUpdate(task.result?.user)
                } else {
                    Toast.makeText(
                        activity,
                        activity.resources.getString(R.string.sign_up_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    fun signInWithEmail(email: String, password: String) {
        if(email.isNotEmpty() && password.isNotEmpty()) {
            activity.myAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                    task ->
                if (task.isSuccessful) {
                    activity.uiUpdate(task.result?.user)
                } else {
                    Toast.makeText(
                        activity,
                        activity.resources.getString(R.string.sign_in_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun sendEmailVerification(user: FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener{
            task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    activity,
                    activity.resources.getString(R.string.send_verification_email_done),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    activity,
                    activity.resources.getString(R.string.send_verification_email_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}