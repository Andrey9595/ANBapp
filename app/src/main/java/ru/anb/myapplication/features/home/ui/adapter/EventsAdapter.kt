package ru.anb.myapplication.features.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.anb.myapplication.databinding.CardEventBinding
import ru.anb.myapplication.features.home.domain.model.EventsModel

class EventsAdapter() : PagingDataAdapter<EventsModel, EventsViewHolder>(EventsDiffCallback()) {


    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val binding = CardEventBinding.inflate(LayoutInflater.from(parent.context))
        return EventsViewHolder(binding)
    }
}

class EventsViewHolder(private val binding: CardEventBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EventsModel) {
        with(binding) {
            this.dateOfEvent.text = item.datetime
        }
    }

}

class EventsDiffCallback : DiffUtil.ItemCallback<EventsModel>() {
    override fun areItemsTheSame(oldItem: EventsModel, newItem: EventsModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventsModel, newItem: EventsModel): Boolean {
        return oldItem == newItem
    }
}
