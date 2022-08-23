package com.udacity.shoestore

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.database.Shoe
import com.udacity.shoestore.screens.ShoeAdapter

//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data : List<Shoe>) {
//    val adapter = recyclerView.adapter as ShoeAdapter
//    adapter.submitList(data)
//}

@BindingAdapter("shoeName")
fun TextView.setShoeName(item : Shoe?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("shoeSize")
fun TextView.setShoeSize(item : Shoe?) {
    item?.let {
        text = item.size.toString()
    }
}

@BindingAdapter("shoeCompany")
fun TextView.setShoeCompany(item : Shoe?) {
    item?.let {
        text = item.company
    }
}

@BindingAdapter("shoeDescription")
fun TextView.setShoeDescription(item : Shoe?) {
    item?.let {
        text = item.description
    }
}