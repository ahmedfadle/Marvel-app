package com.marvel.app.presentation.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvel.app.common.extentions.loadImage
import com.marvel.app.common.utils.Utils
import com.marvel.app.databinding.ItemDetailsBinding
import com.marvel.app.domain.models.Item

class DetailsItemRecyAdapter(
    private val dataList: ArrayList<Item>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<DetailsItemRecyAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(private val binding: ItemDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.tvTitle.text = item.name
            binding.ivCover.loadImage(Utils.getFullImagePath(item.resourceURI ?: "", "jpg"))
            binding.root.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsItemRecyAdapter.ItemViewHolder =
        ItemViewHolder(
            ItemDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: DetailsItemRecyAdapter.ItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickListener {
        fun onItemClick(item: Item)
    }

    fun updateData(data: List<Item>) {

        dataList.apply {
            clear()
            addAll(data)
        }
    }
}