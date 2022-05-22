package com.intermediait.marvel.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intermediait.marvel.databinding.ItemComicBinding
import com.intermediait.marvel.domain.models.Comic
import com.intermediait.marvel.view.adapter.ComicsAdapter.ComicsViewHolder

class ComicsAdapter(private val comics: List<Comic>): RecyclerView.Adapter<ComicsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ComicsViewHolder {
        val binding = ItemComicBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ComicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val comic = comics[position]
        holder.binding.comic = comic
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return comics.size
    }

    inner class ComicsViewHolder(val binding: ItemComicBinding) : RecyclerView.ViewHolder(binding.root) {
    }

}