package ru.anb.myapplication.features.home.domain

import android.content.Context
import android.content.Intent

interface ContentInteraction<T: Any> {

    fun onLike(t: T)

    fun onRemove(t: T)

    fun onShare(c: Context, t: T): Intent
}