package com.example.bionetsample.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bionetsample.R
import com.example.bionetsample.data.RegionItem
import com.example.bionetsample.data.Regions
import com.example.bionetsample.databinding.FragmentSignInBinding
import com.example.bionetsample.viewModel.SignInViewModel

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModels()
    private var region: Int = 2
    private val regionsAdapter: ArrayAdapter<RegionItem> by lazy {
        ArrayAdapter(
            requireContext(),
            R.layout.sign_in_dropdown_item,
            mutableListOf<RegionItem>()
        )
    }

//    private val regionSelectedListener by lazy {
//        AdapterView.OnItemClickListener { _, _, position, _ ->
//            region = regionsAdapter.getItem(position)!!.id
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (binding.regionsSpinner.editText as? AutoCompleteTextView)?.setAdapter(regionsAdapter)

        viewModel.regions.observe(viewLifecycleOwner) {
            regionsAdapter.clear()
            regionsAdapter.addAll(it)
            regionsAdapter.notifyDataSetChanged()
            Log.d("SignInFragment", "$it")
        }
    }

}