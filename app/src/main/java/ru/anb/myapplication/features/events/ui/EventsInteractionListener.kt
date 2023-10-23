package ru.anb.myapplication.features.events.ui

import android.content.Intent
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
        TODO("Not yet implemented")
    }

    override fun onShare(t: EventsModel): Intent {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND

            val author = t.author
            val content = t.content
            val attach = t.attachment?.url?:""
            val date = t.datetime.take(19).toLocalDateTime().asString()
            val link = t.link?:""
//            val format = t.type
            val downloadLink = "https://disk.yandex.ru/d/CMfR6397IROBqw"
            val msg =
                "$author делится мероприятием:\n" +
                        "$content \n." +
                        "Начало $date \n"+
//                        "Формат мероприятия: $format\n"+
                        "вложение: $attach \n"+
                        "сайт: $link\n"+
                        "отправлено из NeWork App.\n"+
                        "чтобы скачать приложение пройдите по ссылке: \n"+
                        downloadLink

            putExtra(Intent.EXTRA_TEXT, msg)
            type = "text/plain"
        }

    return  intent
    }
}