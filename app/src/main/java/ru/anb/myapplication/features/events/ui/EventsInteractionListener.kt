package ru.anb.myapplication.features.events.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import ru.anb.myapplication.R
import ru.anb.myapplication.core.extensions.asString
import ru.anb.myapplication.core.extensions.toLocalDateTime
import ru.anb.myapplication.features.events.domain.EventsInteraction
import ru.anb.myapplication.features.home.domain.model.EventsModel

class EventsInteractionListener(private val viewModel: EventsViewModel) : EventsInteraction {

    override fun onTakePartBtn(event: EventsModel) {
        if (event.participatedByMe)
            viewModel.removeParticipation(event)
        else viewModel.takePart(event)
    }

    override fun onLike(t: EventsModel) {
        if (t.likedByMe)
            viewModel.disLike(t)
        else viewModel.like(t)
    }

    override fun onRemove(t: EventsModel) {
        viewModel.removeEvent(t)
    }

    @SuppressLint("StringFormatMatches")
    override fun onShare(c: Context, t: EventsModel): Intent {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND

            val author = t.author
            val content = t.content
            val attach = t.attachment?.url ?: ""
            val date = t.datetime.take(19).toLocalDateTime()
                .asString() // 19 обозначаает число символов, которое передается на сервер, без этого числа при передаче данных будет ошибка 400
            val link = t.link ?: ""
            val downloadLink = "https://disk.yandex.ru/d/CMfR6397IROBqw"
            val msg =
                c.getString(R.string.share_name, author, content, date, attach, link, downloadLink)

            putExtra(Intent.EXTRA_TEXT, msg)
            type = "text/plain"
        }

        return intent
    }
}