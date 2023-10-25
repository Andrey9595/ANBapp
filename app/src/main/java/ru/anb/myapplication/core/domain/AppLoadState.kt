package ru.anb.myapplication.core.domain

import android.content.Context
import androidx.annotation.StringRes

abstract class AppLoadState<T> {

    open class Success<T>(open val data: T) : AppLoadState<T>()

    class Error<T>(
        @StringRes private val msgId: Int? = null,
        private val msgString: String? = null
    ) :
        AppLoadState<T>() {

        fun asString(context: Context): String {
            return msgId?.let { context.getString(it) } ?: msgString ?: "Error"
        }
    }

    class Loading<T> : AppLoadState<T>()

    class NotLoadedYet<T> : AppLoadState<T>()


}