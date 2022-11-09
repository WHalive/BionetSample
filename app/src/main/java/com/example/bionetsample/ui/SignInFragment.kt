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
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.bionetsample.R
import com.example.bionetsample.data.RegionItem
import com.example.bionetsample.data.SchoolItem
import com.example.bionetsample.data.SchoolTypeItem
import com.example.bionetsample.databinding.FragmentSignInBinding
import com.example.bionetsample.entity.RegionEntity
import com.example.bionetsample.viewModel.SignInViewModel

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModels()

    //    private var region: Int = 2
    private val regionsAdapter: ArrayAdapter<RegionEntity> by lazy {
        ArrayAdapter(
            requireContext(),
            R.layout.sign_in_dropdown_item,
            mutableListOf<RegionEntity>()
        )
    }

//    private val regionSelectedListener by lazy {
//        AdapterView.OnItemClickListener { _, _, position, _ ->
//            region = regionsAdapter.getItem(position)!!.id
//        }
//    }

    private val schoolTypesAdapter: ArrayAdapter<SchoolTypeItem> by lazy {
        ArrayAdapter(
            requireContext(),
            R.layout.sign_in_dropdown_item,
            mutableListOf(
                SchoolTypeItem(1, "Maktab"),
                SchoolTypeItem(2, "Kollej"),
                SchoolTypeItem(3, "Oliy o\'quv yurti"),
            )
        )
    }

    private val schoolsAdapter: ArrayAdapter<SchoolItem> by lazy {
        ArrayAdapter(
            requireContext(),
            R.layout.sign_in_dropdown_item,
            mutableListOf<SchoolItem>()
        )
    }

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
        (binding.typesSpinner.editText as? AutoCompleteTextView)?.setAdapter(schoolTypesAdapter)
        (binding.schoolsSpinner.editText as? AutoCompleteTextView)?.setAdapter(schoolsAdapter)

        viewModel.regions.observe(viewLifecycleOwner) { regions ->
            regionsAdapter.clear()
            regionsAdapter.addAll(regions)
            regionsAdapter.notifyDataSetChanged()

        }

        viewModel.schoolTypes.observe(viewLifecycleOwner) {
            schoolTypesAdapter.clear()
            schoolTypesAdapter.addAll(it)
            schoolTypesAdapter.notifyDataSetChanged()
        }

        viewModel.schools.observe(viewLifecycleOwner) {
            schoolsAdapter.clear()
            schoolsAdapter.addAll(it)
            schoolsAdapter.notifyDataSetChanged()
        }


        binding.regButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, SingUpFragment(), "SignUpFragment")
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}