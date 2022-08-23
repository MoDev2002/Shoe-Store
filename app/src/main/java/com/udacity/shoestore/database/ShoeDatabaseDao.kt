package com.udacity.shoestore.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShoeDatabaseDao {
    @Insert
    fun insert(shoe : Shoe)

    @Query("select * from shoe_table order by id desc")
    fun getAll() : LiveData<List<Shoe>>

    @Query("delete from shoe_table where id = :id")
    fun deleteShoe(id : Long)

    @Query("delete from shoe_table")
    fun deleteAll()
}