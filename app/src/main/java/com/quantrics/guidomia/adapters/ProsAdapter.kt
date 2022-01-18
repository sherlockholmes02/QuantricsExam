package com.quantrics.guidomia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quantrics.guidomia.R
import com.quantrics.guidomia.databinding.ItemProsConsBinding

class ProsAdapter : ListAdapter<String, ProsAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_pros_cons,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val item = currentList[holder.absoluteAdapterPosition]
            holder.binding.value = item
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    class ViewHolder(val binding: ItemProsConsBinding) : RecyclerView.ViewHolder(binding.root)
}