package com.udacity.shoestore.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.database.Shoe
import com.udacity.shoestore.databinding.ShoeItemBinding

class ShoeAdapter(val clickListener: OnClickListener) : ListAdapter<Shoe, ShoeAdapter.ViewHolder>(DiffCallBack) {
    class ViewHolder(private var binding : ShoeItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(shoe: Shoe, clickListener: OnClickListener) {
            binding.shoe = shoe
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Shoe>() {
        override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeAdapter.ViewHolder {
        return ViewHolder(ShoeItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ShoeAdapter.ViewHolder, position: Int) {
        val shoe = getItem(position)
        holder.bind(shoe, clickListener)
    }

}

class OnClickListener(val clickListener: (id : Long) -> Unit) {
    fun onClick(shoe : Shoe) = clickListener(shoe.id)
}