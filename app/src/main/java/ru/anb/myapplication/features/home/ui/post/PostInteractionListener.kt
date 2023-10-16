package ru.anb.myapplication.features.home.ui.post

import android.content.Intent
import ru.anb.myapplication.features.home.domain.model.PostModel
import ru.anb.myapplication.features.home.domain.posts.PostInteraction

class PostInteractionListener(private val postsViewModel: PostsViewModel) : PostInteraction {

    override fun onLike(t: PostModel) {
        if (t.likedByMe)
            postsViewModel.dislike(t)
        else postsViewModel.like(t)
    }


    override fun onRemove(t: PostModel) {
        postsViewModel.removePost(t)
    }

    override fun onShare(t: PostModel): Intent {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND

            val author = t.author
            val content = t.content
            val attach = t.attachment?.url ?: ""
            val link = t.link ?: ""
//            val format = t.type
            val downloadLink = "https://"
            val msg =
                "$author делится постом:\n" +
                        "$content \n." +
//                        "Формат мероприятия: $format\n"+
                        "вложение: $attach \n" +
                        "сайт: $link\n" +
                        "отправлено из NeWork App.\n" +
                        "чтобы скачать приложение пройдите по ссылке: \n" +
                        downloadLink

            putExtra(Intent.EXTRA_TEXT, msg)
            type = "text/plain"
        }

        return intent
    }
}