package com.example.bionetsample.ui

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Build.VERSION_CODES.N
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.bionetsample.R
import com.example.bionetsample.databinding.ActivityMainBinding
import com.example.bionetsample.extensions.isOnline
import com.example.bionetsample.extensions.showNetworkWarning
import com.example.bionetsample.viewModel.BionetViewModel
import java.lang.reflect.Array.get

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, SignInFragment.newInstance())
            .commit()
    }
}


//        if (!isNetworkAvailable) {
//            AlertDialog.Builder(this)
//                .setIcon(R.drawable.icon_bionet)
//                .setTitle("B I O N E T")
//                .setMessage("Iltimos internetga ulanib, qaytadan urinib ko'ring.")
//                .setPositiveButton("OK") { dialogInterface, i ->
//                    finish()
//                }.show()
//        } else if (isNetworkAvailable) {
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.fragmentContainer, SignInFragment.newInstance())
//                .commit()
//        }
//
//    val isNetworkAvailable: Boolean
//        get() {
//            val connectivityManager =
//                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                val capabilities =
//                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//                if (capabilities != null) {
//                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                        return true
//                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                        return true
//                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
//                        return true
//                    }
//                }
//            }
//            return false
//
//        }
