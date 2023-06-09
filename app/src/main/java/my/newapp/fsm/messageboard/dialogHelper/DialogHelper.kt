package my.newapp.fsm.messageboard.dialogHelper

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import my.newapp.fsm.messageboard.MainActivity
import my.newapp.fsm.messageboard.R
import my.newapp.fsm.messageboard.accountHelper.AccountHelper
import my.newapp.fsm.messageboard.databinding.SignDialogBinding
import my.newapp.fsm.messageboard.dialogHelper.DialogConst.SIGN_UP_STATE

class DialogHelper(act: MainActivity) {
    private val activity = act
    private val accHelper = AccountHelper(activity)

    fun createSignDialog(index: Int) {
        val builder = AlertDialog.Builder(activity)
        val binding = SignDialogBinding.inflate(activity.layoutInflater)
        builder.setView(binding.root)
        setDialogState(index, binding)
        val dialog = builder.create()
        binding.btSignUpIn.setOnClickListener {
            setOnClickSignUpIn(index, binding, dialog)
        }
        binding.btForgetPass.setOnClickListener {
            setOnClickResetPassword(binding, dialog)
        }
        dialog.show()
    }

    private fun setOnClickResetPassword(binding: SignDialogBinding, dialog: AlertDialog?) {
        if (binding.edSignEmail.text.isNotEmpty()) {
            activity.myAuth.sendPasswordResetEmail(binding.edSignEmail.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(activity, R.string.email_reset_password, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            dialog?.dismiss()
        } else {
            binding.tvDialogMessage.visibility = View.VISIBLE
        }
    }

    private fun setOnClickSignUpIn(index: Int, binding: SignDialogBinding, dialog: AlertDialog?) {
        dialog?.dismiss()
        if (index == SIGN_UP_STATE) {
            accHelper.signUpWithEmail(
                binding.edSignEmail.text.toString(),
                binding.edSignPassword.text.toString()
            )
        } else {
            accHelper.signInWithEmail(
                binding.edSignEmail.text.toString(),
                binding.edSignPassword.text.toString()
            )
        }
    }

    private fun setDialogState(index: Int, binding: SignDialogBinding) {
        if (index == SIGN_UP_STATE) {
            binding.tvSignTitle.text = activity.resources.getString(R.string.ad_sign_up)
            binding.btSignUpIn.text = activity.resources.getString(R.string.sign_up_action)
        } else {
            binding.tvSignTitle.text = activity.resources.getString(R.string.ad_sign_in)
            binding.btSignUpIn.text = activity.resources.getString(R.string.sign_in_action)
            binding.btForgetPass.visibility = View.VISIBLE
        }
    }


}