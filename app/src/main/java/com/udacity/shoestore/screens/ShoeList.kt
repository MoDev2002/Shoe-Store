package com.udacity.shoestore.screens

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoelistFragmentBinding
import com.udacity.shoestore.viewModels.ShoeViewModel
import kotlinx.android.synthetic.main.shoe_item.view.*
import java.net.URL

class ShoeList : Fragment() {

    private val binding by lazy {
        ShoelistFragmentBinding.inflate(layoutInflater)
    }
    private val sharedViewModel : ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel.shoeList.observe(viewLifecycleOwner) { shoeList ->
            binding.shoeList.removeAllViews()
            for (shoe in shoeList) {
                addShoe(shoe.name, shoe.size, shoe.company, shoe.description)
            }
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ShoeListDirections.actionShoeListToShoeDetail())
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.signout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun addShoe(name : String, size : Double, company : String, description : String) {
        val view = layoutInflater.inflate(R.layout.shoe_item, binding.shoeList, false)
        view.shoeName.text = name
        view.shoeSize.text = size.toString()
        view.shoeCompany.text = company
        view.shoeDescription.text = description
        binding.shoeList.addView(view)
    }
}