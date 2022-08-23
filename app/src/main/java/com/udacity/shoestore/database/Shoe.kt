package com.udacity.shoestore.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shoe_table")
data class Shoe(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "shoe_name")
    val name: String,

    @ColumnInfo(name = "shoe_size")
    val size: Double,

    @ColumnInfo(name = "shoe_company")
    val company: String,

    @ColumnInfo(name = "shoe_description")
    val description: String

)