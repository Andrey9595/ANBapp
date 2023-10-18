package ru.anb.myapplication.core.extensions


import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.anb.myapplication.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun LocalDateTime.asString(): String = format(DateTimeFormatter.ofPattern("dd MMMM yyyy в HH:mm"))

fun String.toLocalDateTime(): LocalDateTime =
    LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))

fun String.toLocalDateTimeWhithoutZone(): LocalDateTime =
    LocalDateTime.parse(this, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))

fun ImageView.load(url: String) =
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_baseline_no_photography_24)
        .centerCrop()
        .timeout(20_000)
        .into(this)

