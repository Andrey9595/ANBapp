package ru.anb.myapplication.core.extensions


import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun LocalDateTime.asString(): String = format(DateTimeFormatter.ofPattern("dd MMMM yyyy в HH:mm"))

fun String.toLocalDateTime(): LocalDateTime = LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))