package com.udacity.shoestore.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {
    private lateinit var shoe : Shoe
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList : LiveData<MutableList<Shoe>>
        get() = _shoeList
    var shoeName = ""
    var shoeSize = ""
    var shoeCompany = ""
    var shoeDescription = ""

    init {
        _shoeList.value = mutableListOf()
        addShoe("Adidas Max", 42.0, "Adidas", "A shoes for running like a champ")
        addShoe("Nike Air", 39.0, "Nike", "A shoes for the gym")
    }
    fun resetNewShoe() {
        shoeName = ""
        shoeSize = ""
        shoeCompany = ""
        shoeDescription = ""

    }
    fun addNewShoe() : Boolean {
        if(
            shoeName.isBlank() ||
            shoeSize.isBlank() ||
            shoeCompany.isBlank() ||
            shoeDescription.isBlank()) return false

        addShoe(shoeName, shoeSize.toDouble(), shoeCompany, shoeDescription)

        return true
    }
    private fun addShoe(name : String, size : Double, company : String, description : String){
        val newShoe = Shoe(name, size, company, description)
            _shoeList.value!!.add(newShoe)
        }
    }

