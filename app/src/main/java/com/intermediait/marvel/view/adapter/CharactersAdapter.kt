package com.intermediait.marvel.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.intermediait.marvel.databinding.ItemCharacterBinding
import com.intermediait.marvel.view.adapter.CharactersAdapter.CharacterViewHolder
import com.intermediait.marvel.domain.models.Character


class CharactersAdapter(
    diffCallback: DiffUtil.ItemCallback<Character>,
) :
    PagingDataAdapter<Character, CharacterViewHolder>(diffCallback) {

    lateinit var onCharacterClickListener: (Character) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.binding.character = character
        holder.binding.root.setOnClickListener {
            if (character != null) {
                onCharacterClickListener(character)
            }
        }
        holder.binding.executePendingBindings()
    }

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

}


object CharacterComparator : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}