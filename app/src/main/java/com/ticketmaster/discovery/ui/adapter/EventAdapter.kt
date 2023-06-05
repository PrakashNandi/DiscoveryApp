package com.ticketmaster.discovery.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ticketmaster.discovery.databinding.EventItemRowBinding
import com.ticketmaster.discovery.model.Event

class EventAdapter(private val context: Context, private val events: MutableList<Event>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(val binding: EventItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return EventViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(viewHolder: EventViewHolder, position: Int) {
        val event = events[position]
        val firstImage = event.images[0]
        Glide.with(context)
            .load(firstImage.url)
            .into(viewHolder.binding.imageView)
        viewHolder.binding.nameTv.text = event.name
        viewHolder.binding.typeTv.text = event.type
        viewHolder.binding.localeTv.text = event.locale
    }
}