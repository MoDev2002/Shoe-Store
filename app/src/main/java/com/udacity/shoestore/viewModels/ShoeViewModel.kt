package com.udacity.shoestore.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.database.Shoe
import com.udacity.shoestore.database.ShoeDatabase
import com.udacity.shoestore.database.ShoeDatabaseDao
import kotlinx.coroutines.*
import timber.log.Timber

class ShoeViewModel(val database : ShoeDatabaseDao) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val shoeList = database.getAll()

    var shoeName = ""
    var shoeSize = ""
    var shoeCompany = ""
    var shoeDescription = ""

    init {

    }
    fun resetNewShoe() {
        shoeName = ""
        shoeSize = ""
        shoeCompany = ""
        shoeDescription = ""

    }
    fun addNewShoe() : Boolean {
        uiScope.launch {
            if(
                shoeName.isBlank() ||
                shoeSize.isBlank() ||
                shoeCompany.isBlank() ||
                shoeDescription.isBlank()) return@launch

            insert(shoeName, shoeSize.toDouble(), shoeCompany, shoeDescription)
        }
        return true
    }
    private suspend fun insert(name : String, size : Double, company : String, description : String){
        val newShoe = Shoe(name = name, size = size, company = company, description = description)
        withContext(Dispatchers.IO) {
            database.insert(newShoe)
        }
    }

    fun deleteShoe(id : Long){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.deleteShoe(id)
            }
        }
    }

    fun deleteAll() : Boolean {
        uiScope.launch{
            withContext(Dispatchers.IO) {
                database.deleteAll()
            }
        }
        return true
    }
    }

