package ru.anb.myapplication.features.home.db

abstract class ContentEntity<T> {
    abstract val id: Long
    abstract fun toDomainModel(): T
}