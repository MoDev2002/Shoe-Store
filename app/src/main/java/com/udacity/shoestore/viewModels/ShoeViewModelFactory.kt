package com.udacity.shoestore.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.database.ShoeDatabaseDao

class ShoeViewModelFactory(private val dataSource : ShoeDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ShoeViewModel::class.java)) {
            return ShoeViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}