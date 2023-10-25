package ru.anb.myapplication.features.posts.ui

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.net.toUri
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.anb.myapplication.R
import ru.anb.myapplication.core.extensions.asString
import ru.anb.myapplication.core.extensions.load
import ru.anb.myapplication.core.extensions.toLocalDateTime
import ru.anb.myapplication.databinding.CardPostBinding
import ru.anb.myapplication.features.home.domain.model.AttachmentType
import ru.anb.myapplication.features.home.domain.model.PostModel

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
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { itemMenu ->
                        when (itemMenu.itemId) {
                            R.id.remove -> {
                                postInteractionListener.onRemove(item)
                                true
                            }

                            else -> {
                                true
                            }
                        }
                    }
                }.show()
            }
            if (item.attachment != null && item.attachment.url.startsWith("http", false)) {
                attachLayout.root.visibility = View.VISIBLE
                when (item.attachment.attachmentType) {
                    AttachmentType.IMAGE -> {
                        attachLayout.attachImage.visibility = View.VISIBLE
                        attachLayout.attachImage.load(item.attachment.url)
                    }

                    AttachmentType.VIDEO -> {
                        attachLayout.attachVideo.visibility = View.VISIBLE
                        attachLayout.attachVideo.setVideoURI(item.attachment.url.toUri())
                        attachLayout.attachVideo.start()
                    }

                    AttachmentType.AUDIO -> {
                        attachLayout.attachAudio.visibility = View.VISIBLE
                        val mediaPlayer = MediaPlayer()
                        attachLayout.attachAudio.setOnClickListener {
                            if (!mediaPlayer.isPlaying) {
                                mediaPlayer.setDataSource(
                                    binding.root.context,
                                    item.attachment.url.toUri()
                                )
                                mediaPlayer.prepare()
                                mediaPlayer.start()
                            }
                        }
                    }
                }
            } else {
                binding.attachLayout.root.visibility = View.GONE
            }
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
            interactionPosts.shareBtn.setOnClickListener {
                val intent = postInteractionListener.onShare(binding.root.context, item)
                binding.root.context.startActivity(intent)
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