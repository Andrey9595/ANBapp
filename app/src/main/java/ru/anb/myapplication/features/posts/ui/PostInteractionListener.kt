package ru.anb.myapplication.features.posts.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import ru.anb.myapplication.R
import ru.anb.myapplication.features.home.domain.model.PostModel
import ru.anb.myapplication.features.posts.domain.PostInteraction

class PostInteractionListener(private val postsViewModel: PostsViewModel) : PostInteraction {

    override fun onLike(t: PostModel) {
        if (t.likedByMe)
            postsViewModel.dislike(t)
        else postsViewModel.like(t)
    }


    override fun onRemove(t: PostModel) {
        postsViewModel.removePost(t)
    }

    @SuppressLint("StringFormatMatches")
    override fun onShare(c: Context, t: PostModel): Intent {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND

            val author = t.author
            val content = t.content
            val attach = t.attachment?.url ?: ""
            val link = t.link ?: ""
            val downloadLink = "https://"
            val msg = c.getString(R.string.post_name, author, content, attach, link, downloadLink)


            putExtra(Intent.EXTRA_TEXT, msg)
            type = "text/plain"
        }

        return intent
    }
}