package com.example.bionetsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bionetsample.R
import com.example.bionetsample.databinding.ActivityMainBinding

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