package com.example.bionetsample.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.bionetsample.R
import com.example.bionetsample.data.RegionItem
import com.example.bionetsample.data.SchoolItem
import com.example.bionetsample.data.SchoolTypeItem
import com.example.bionetsample.databinding.FragmentSignInBinding
import com.example.bionetsample.extensions.isOnline
import com.example.bionetsample.extensions.showNetworkWarning
import com.example.bionetsample.viewModel.BionetViewModel
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: BionetViewModel by viewModels()
    private var region: Int = 2
    private var schoolTypeItem: Int = 1
    private var school: Int = 1

    private val regionsAdapter: ArrayAdapter<RegionItem> by lazy {
        ArrayAdapter(
            requireContext(),
            R.layout.sign_in_dropdown_item,
            mutableListOf<RegionItem>()
        )
    }

    private val regionSelectedListener by lazy {
        AdapterView.OnItemClickListener { _, _, position, _ ->
            region = regionsAdapter.getItem(position)!!.id
            viewModel.getAllRegions()
        }
    }

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

    private val schoolTypeSelectedListener by lazy {
        AdapterView.OnItemClickListener { _, _, position, _ ->
            schoolTypeItem = schoolTypesAdapter.getItem(position)!!.id
            viewModel.getAllSchools(region, schoolTypeItem)
        }
    }

    private val schoolsAdapter: ArrayAdapter<SchoolItem> by lazy {
        ArrayAdapter(
            requireContext(),
            R.layout.sign_in_dropdown_item,
            mutableListOf<SchoolItem>()
        )
    }

    private val schoolSelectedListener by lazy {
        AdapterView.OnItemClickListener { _, _, position, _ ->
            school = schoolsAdapter.getItem(position)!!.id
        }
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

        if (!checkNetwork()) {
            return
        }
        val regionsSpinner = binding.regionsSpinner.editText as AutoCompleteTextView
        regionsSpinner.setAdapter(regionsAdapter)
        regionsSpinner.onItemClickListener = regionSelectedListener

        val schoolTypesSpinner = binding.typesSpinner.editText as AutoCompleteTextView
        schoolTypesSpinner.setAdapter(schoolTypesAdapter)
        schoolTypesSpinner.onItemClickListener = schoolTypeSelectedListener

        val schoolsSpinner = binding.schoolsSpinner.editText as AutoCompleteTextView
        schoolsSpinner.setAdapter(schoolsAdapter)
        schoolsSpinner.onItemClickListener = schoolSelectedListener

        viewModel.regions.observe(viewLifecycleOwner) { regions ->
            regionsAdapter.clear()
            regionsAdapter.addAll(regions)
            regionsAdapter.notifyDataSetChanged()
            (binding.regionsSpinner.editText as AutoCompleteTextView).setText(regions.find { it.id == region }
                .toString(), false)
            showTypesSpinner()
            showSchoolsSpinner()
        }
        binding.regButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, SingUpFragment(), "SignUpFragment")
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!checkNetwork()) {
            return
        }
    }

    private fun showTypesSpinner() {
        schoolTypesAdapter.notifyDataSetChanged()
        (binding.typesSpinner.editText as AutoCompleteTextView).setText(
            schoolTypesAdapter.getItem(0).toString(), false
        )
        viewModel.getAllSchools(region, schoolTypeItem)
    }

    private fun showSchoolsSpinner() {
        viewModel.schools.observe(viewLifecycleOwner) { schools ->
            schoolsAdapter.clear()
            schoolsAdapter.addAll(schools)
            schoolsAdapter.notifyDataSetChanged()
            (binding.schoolsSpinner.editText as AutoCompleteTextView).setText(
                schools.first().toString(), false
            )
            school = schools[0].id

            Log.d("signInFragment", "$schools")
        }
    }

    private fun checkNetwork(): Boolean {
        return if (requireActivity().isOnline()) {
            true
        } else {
            showNetworkWarning()
            false
        }
    }

}
