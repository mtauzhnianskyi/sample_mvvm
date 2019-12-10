package com.example.mvvm.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.databinding.ItemLayoutBinding
import com.example.mvvm.ui.main.holder.ItemHolder
import com.example.mvvm.ui.main.repo.Data
import com.squareup.picasso.Picasso

@BindingAdapter("bind:setUrl")
fun ImageView.loadImage(imageUrl: String){
    Picasso.get()
        .load(imageUrl)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_foreground)
        .fit()
        .into(this)

}
class RecyclerAdapter(private val items: ArrayList<Data>) : RecyclerView.Adapter<ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(itemBinding.root)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.itemBinding?.listItem = items[position]
    }

    fun update(items: List<Data>){
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}
