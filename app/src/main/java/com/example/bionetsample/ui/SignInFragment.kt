package com.example.bionetsample.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bionetsample.databinding.FragmentSignInBinding
import com.example.bionetsample.viewModel.SignInViewModel

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        viewModel.regions.observe(viewLifecycleOwner) { regions ->
            regions
            Log.d("SignInFragment", "$regions")
        }

        return binding.root
    }

}