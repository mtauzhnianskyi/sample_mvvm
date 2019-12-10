package com.example.mvvm.ui.main.holder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.ItemLayoutBinding

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var itemBinding : ItemLayoutBinding? = DataBindingUtil.bind(itemView)
}