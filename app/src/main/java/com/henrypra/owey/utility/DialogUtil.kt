package com.henrypra.owey.utility

import android.app.AlertDialog
import android.content.Context
import com.henrypra.owey.R

object DialogUtil {

    fun buildCreationErrorDialog(context: Context?) {
        AlertDialog.Builder(context).setTitle(R.string.error_title)
                .setMessage(R.string.error_wrong_input_body)
                .setPositiveButton(R.string.understood, null).setCancelable(true).show()
    }

}