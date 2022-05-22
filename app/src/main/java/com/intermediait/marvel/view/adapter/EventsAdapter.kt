package com.intermediait.marvel.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intermediait.marvel.databinding.ItemEventBinding
import com.intermediait.marvel.domain.models.Event
import com.intermediait.marvel.view.adapter.EventsAdapter.EventViewHolder
import kotlin.properties.Delegates


class EventsAdapter: RecyclerView.Adapter<EventViewHolder>() {
    var items: List<Event> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EventViewHolder {
        val binding = ItemEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = items[position]
        holder.binding.event = event
        val adapter = ComicsAdapter(event.comics)
        holder.binding.rvComics.adapter = adapter
        holder.binding.btnDropdown.setOnClickListener {
            if(event.state == View.GONE){
                event.state = View.VISIBLE
            }else if(event.state == View.VISIBLE){
                event.state = View.GONE
            }
            holder.binding.event = event
        }
        holder.binding.executePendingBindings()

    }

    inner class EventViewHolder(val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root)

}