package ru.anb.myapplication.features.home.domain

import android.content.Intent

interface ContentInteraction<T: Any> {

    fun onLike(t: T)

    fun onEdit(t: T)

    fun onRemove(t: T)

    fun onShare(t: T): Intent
}