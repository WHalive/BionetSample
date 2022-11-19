package com.example.bionetsample.extensions

import android.os.Build
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.bionetsample.R
import com.example.bionetsample.ui.SignInFragment

fun Fragment.showNetworkWarning(): AlertDialog =
    AlertDialog.Builder(activity!!)
        .setTitle(getString(R.string.dialog_title))
        .setMessage(getString(R.string.network_error))
        .setPositiveButton(R.string.ok) { _, _ ->
            activity?.finishAndRemoveTask()
        }
        .setCancelable(false)
        .setIcon(R.drawable.icon_bionet)
        .show()