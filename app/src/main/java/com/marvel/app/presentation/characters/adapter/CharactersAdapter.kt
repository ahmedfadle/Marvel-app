package com.marvel.app.presentation.characters.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.marvel.app.common.extentions.loadImage
import com.marvel.app.common.utils.Utils
import com.marvel.app.databinding.ItemCharacterBinding
import com.marvel.app.domain.models.CharacterResult


class CharactersAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<CharacterResult, CharactersAdapter.GridViewHolder>(COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {

        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(binding)

    }


    inner class GridViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(character: CharacterResult) {
            character.name?.let {
                binding.containerName.visibility = View.VISIBLE
                binding.tvName.text = it
            } ?: { binding.containerName.visibility = View.GONE }


            binding.ivCharacterBg.loadImage(Utils.getFullImagePath(character.thumbnail?.path?: "",character.thumbnail?.extension ?: ""))
//            Glide.with(binding.ivCharacterBg.context)
//                .load(character.thumbnail?.path?.replace("http","https") + "." + character.thumbnail?.extension).into(binding.ivCharacterBg)


        }
    }


    interface OnItemClickListener {
        fun onItemClick(photo: CharacterResult)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CharacterResult>() {
            override fun areItemsTheSame(oldItem: CharacterResult, newItem: CharacterResult) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CharacterResult, newItem: CharacterResult) =
                oldItem == newItem
        }
    }


    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

}