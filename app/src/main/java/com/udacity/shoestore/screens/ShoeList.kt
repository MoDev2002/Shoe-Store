package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.database.ShoeDatabase
import com.udacity.shoestore.databinding.ShoelistFragmentBinding
import com.udacity.shoestore.viewModels.ShoeViewModel
import com.udacity.shoestore.viewModels.ShoeViewModelFactory


class ShoeList : Fragment() {

    private val binding by lazy {
        ShoelistFragmentBinding.inflate(layoutInflater)
    }

    private lateinit var sharedViewModel: ShoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dataSource = ShoeDatabase.getInstance(application).shoeDatabaseDao

        //Initialize the ViewModel
        val sharedViewModelFactory = ShoeViewModelFactory(dataSource)
        sharedViewModel = ViewModelProvider(requireActivity(), sharedViewModelFactory).get(ShoeViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Initialize the Database

        binding.lifecycleOwner = this
        binding.viewModel = sharedViewModel

        val manager = LinearLayoutManager(activity)

        val adapter = ShoeAdapter(OnClickListener { id -> sharedViewModel.deleteShoe(id) } )

        sharedViewModel.shoeList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


        binding.shoeRecyclerView.layoutManager = manager
        binding.shoeRecyclerView.adapter = adapter

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
        return when(item.itemId) {
            R.id.clearItems -> sharedViewModel.deleteAll()
            R.id.loginFragment -> NavigationUI.onNavDestinationSelected(
                item,
                view!!.findNavController()
            )
            else -> super.onOptionsItemSelected(item)
        }
    }


}