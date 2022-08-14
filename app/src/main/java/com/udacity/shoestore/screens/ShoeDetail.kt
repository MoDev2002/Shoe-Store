package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoedetailFragmentBinding
import com.udacity.shoestore.viewModels.ShoeViewModel

class ShoeDetail : Fragment() {
    private val sharedViewModel : ShoeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflating the fragment
        val binding = DataBindingUtil.inflate<ShoedetailFragmentBinding>(inflater, R.layout.shoedetail_fragment, container, false)

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