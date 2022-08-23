package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.database.ShoeDatabase
import com.udacity.shoestore.databinding.ShoedetailFragmentBinding
import com.udacity.shoestore.viewModels.ShoeViewModel
import com.udacity.shoestore.viewModels.ShoeViewModelFactory

class ShoeDetail : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflating the fragment
        val binding = DataBindingUtil.inflate<ShoedetailFragmentBinding>(inflater, R.layout.shoedetail_fragment, container, false)

        //Initialize the Database
        val application = requireNotNull(this.activity).application
        val dataSource = ShoeDatabase.getInstance(application).shoeDatabaseDao

        //Initialize the ViewModel
        val sharedViewModelFactory = ShoeViewModelFactory(dataSource)
        val sharedViewModel = ViewModelProvider(requireActivity(), sharedViewModelFactory).get(ShoeViewModel::class.java)


        binding.viewModel = sharedViewModel
        sharedViewModel.resetNewShoe()
        //assigning EditText fields to variables
        binding.saveButton.setOnClickListener {
            sharedViewModel.addNewShoe()
            findNavController().navigate(ShoeDetailDirections.actionShoeDetailToShoeList())
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(ShoeDetailDirections.actionShoeDetailToShoeList())
        }

        return binding.root
    }
}