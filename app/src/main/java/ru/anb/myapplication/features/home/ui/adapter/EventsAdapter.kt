package ru.anb.myapplication.features.home.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.anb.myapplication.R
import ru.anb.myapplication.core.extensions.asString
import ru.anb.myapplication.core.extensions.toLocalDateTime
import ru.anb.myapplication.databinding.CardEventBinding
import ru.anb.myapplication.features.home.domain.events.EventsInteraction
import ru.anb.myapplication.features.home.domain.model.EventsModel

class EventsAdapter(private val eventsInteractionListener: EventsInteraction) :
    PagingDataAdapter<EventsModel, EventsViewHolder>(EventsDiffCallback()) {


    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val binding = CardEventBinding.inflate(LayoutInflater.from(parent.context))
        return EventsViewHolder(binding, eventsInteractionListener)
    }
}

class EventsViewHolder(
    private val binding: CardEventBinding,
    private val eventsInteractionListener: EventsInteraction
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EventsModel) {
        with(binding) {
            name.text = item.author
            published.text = item.published.take(19).toLocalDateTime().asString()
            content.text = item.content
            dateOfEvent.text = item.datetime.take(19).toLocalDateTime().asString()
            webText.text = item.link
            typeOfEvent.text = item.type
            speakers.text = item.speakerIds.map { item.users[it]?.name }.joinToString(", ")
            Log.d("aaa", "is liked = ${item.likedByMe}")
            interactionPosts.likeBtn.text = item.likeOwnerIds.size.toString()

            interactionPosts.likeBtn.setOnClickListener {
                eventsInteractionListener.onLike(item)
            }
            interactionPosts.likeBtn.isChecked = item.likedByMe
            interactionPosts.mentioned.text = item.participantsIds.size.toString()
            interactionPosts.takePartBtn.setOnClickListener {
                eventsInteractionListener.onTakePartBtn(item)
            }
            interactionPosts.shareBtn.setOnClickListener {
                val intent = eventsInteractionListener.onShare(item)
                binding.root.context.startActivity(intent)
            }
            Glide.with(avatar.context).load(item.authorAvatar).circleCrop()
                .placeholder(R.drawable.ic_baseline_account_circle_24).into(avatar)
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { itemMenu ->
                        when (itemMenu.itemId) {
                            R.id.remove -> {
                                eventsInteractionListener.onRemove(item)
                                true
                            }

                        else -> {
                            true
                        }
                        }
                    }
                }
            }
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
