package ru.anb.myapplication.core.domain

import androidx.annotation.StringRes

abstract class LoadState<T> {

    open class Success<T>(open val data: T) : LoadState<T>()

    class Error<T>(@StringRes val error: Int) : LoadState<T>()

    class Loading<T> : LoadState<T>()

    class NotLoadedYet<T> : LoadState<T>()

    class UserIsExist<T>(override val data: T) : Success<T>(data)

    class UserIsMotExist<T>(override val data: T) : Success<T>(data)

}