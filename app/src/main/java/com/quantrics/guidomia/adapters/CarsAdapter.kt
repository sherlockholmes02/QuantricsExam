package com.quantrics.guidomia.adapters

import android.os.Build
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quantrics.guidomia.R
import com.quantrics.guidomia.databinding.ItemCarBinding
import com.quantrics.guidomia.model.Car
import com.squareup.picasso.Picasso


class CarsAdapter : ListAdapter<Car, CarsAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean =
            oldItem == newItem
    }
) {

    private var openPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_car,
                parent,
                false
            )
        )

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val item = currentList[holder.absoluteAdapterPosition]
            holder.binding.car = item

            bindUI(holder, item)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun bindUI(holder: ViewHolder, item: Car) {
        var image: Int = R.drawable.ic_broken_image

        when (item.id) {
            1 -> {
                image = R.drawable.range_rover
            }
            2 -> {
                image = R.drawable.alpine_roadster
            }
            3 -> {
                image = R.drawable.bmw_330i
            }
            4 -> {
                image = R.drawable.mercedez_benz_glc
            }
        }

        Picasso.get()
            .load(image)
            .placeholder(R.drawable.ic_broken_image)
            .error(R.drawable.ic_broken_image)
            .into(holder.binding.ivCarImage)

        holder.binding.tvCarPrice.text = ((item.marketPrice / 1000.0).toString()) + "k"

        holder.binding.tvCarPrice.text = if (item.marketPrice % 1000.0 == 0.0) {
            "Price: " + ((item.marketPrice / 1000.0).toInt().toString()) + "k"
        } else {
            "Price: " + ((item.marketPrice / 1000.0).toString()) + "k"
        }

        val prosAdapter = ProsAdapter()
        holder.binding.rvPros.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = prosAdapter
        }
        item.prosList.removeIf { TextUtils.isEmpty(it) }
        prosAdapter.submitList(item.prosList)

        val consAdapter = ConsAdapter()
        holder.binding.rvCons.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = consAdapter
        }
        item.consList.removeIf { TextUtils.isEmpty(it) }
        consAdapter.submitList(item.consList)

        if (holder.absoluteAdapterPosition == 0) {
            holder.binding.clCollapsible.visibility = View.VISIBLE
        }

        holder.itemView.setOnClickListener {
            openPosition = holder.absoluteAdapterPosition
            notifyDataSetChanged()
        }

        setExpandableView(holder)
    }

    private fun setExpandableView(holder: ViewHolder) {
        if (openPosition != holder.absoluteAdapterPosition) {
            holder.binding.clCollapsible.visibility = View.GONE
        } else {
            holder.binding.clCollapsible.visibility = View.VISIBLE
        }
    }

    class ViewHolder(val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root)
}