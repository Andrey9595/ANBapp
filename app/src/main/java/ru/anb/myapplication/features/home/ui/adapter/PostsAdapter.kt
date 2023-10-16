package ru.anb.myapplication.features.home.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.anb.myapplication.R
import ru.anb.myapplication.core.extensions.asString
import ru.anb.myapplication.core.extensions.toLocalDateTime
import ru.anb.myapplication.databinding.CardPostBinding
import ru.anb.myapplication.features.home.domain.model.PostModel
import ru.anb.myapplication.features.home.ui.post.PostInteractionListener

class PostsAdapter(private val postInteractionListener: PostInteractionListener) :
    PagingDataAdapter<PostModel, PostsViewHolder>(PostsDiffCallback()) {
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding, postInteractionListener)
    }
}

class PostsViewHolder(
    private val binding: CardPostBinding,
    private val postInteractionListener: PostInteractionListener
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PostModel) {
        with(binding) {
            Log.d("aaa", "is liked = ${item.likedByMe}")
            name.text = item.author
            published.text = item.published?.take(19)?.toLocalDateTime()?.asString()
            content.text = item.content
            linkText.text = item.link
            interactionPosts.likeBtn.apply {
                setOnClickListener {
                    postInteractionListener.onLike(item)
                }
                isChecked = item.likedByMe
                text = (item.likeOwnerIds?.size ?: 0).toString()
            }
            menu.setOnClickListener {
                postInteractionListener.onRemove(item)
            }
            interactionPosts.shareBtn.setOnClickListener {
                postInteractionListener.onShare(item)
            }
            interactionPosts.mentioned.text = item.mentionIds.size.toString()
            Glide.with(avatar.context).load(item.authorAvatar).circleCrop()
                .placeholder(R.drawable.ic_baseline_account_circle_24).into(avatar)
        }
    }
}

class PostsDiffCallback : DiffUtil.ItemCallback<PostModel>() {
    override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
        return oldItem == newItem
    }
}