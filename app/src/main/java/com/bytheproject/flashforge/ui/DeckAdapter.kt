// src/main/java/com/example/flashintelligence/ui/DeckAdapter.kt
package com.bytheproject.flashforge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bytheproject.flashforge.databinding.ItemDeckBinding
import com.bytheproject.flashforge.model.Deck

class DeckAdapter(private val onItemClick: (Deck) -> Unit) :
    ListAdapter<Deck, DeckAdapter.DeckViewHolder>(DeckDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val binding = ItemDeckBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DeckViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DeckViewHolder(private val binding: ItemDeckBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = absoluteAdapterPosition // Changed here
                if (position != RecyclerView.NO_POSITION) {
                    // It's generally safe to call getItem(position) after checking NO_POSITION
                    // For ListAdapter, getItem() is efficient.
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(deck: Deck) {
            // Make sure these IDs match what's in your item_deck.xml
            binding.textDeckName.text = deck.name
            binding.textDeckDescription.text = deck.description
        }
    }

    private class DeckDiffCallback : DiffUtil.ItemCallback<Deck>() {
        override fun areItemsTheSame(oldItem: Deck, newItem: Deck): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Deck, newItem: Deck): Boolean {
            // For data classes, the default == implementation compares all properties,
            // which is usually what you want for contents.
            return oldItem == newItem
        }
    }
}