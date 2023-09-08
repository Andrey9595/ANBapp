package ru.anb.myapplication.features.home.domain.model

data class CoordinatesModel(
    val latitude: Double,
    val longitude: Double
){
    override fun toString(): String {
        return "lat: $latitude, long: $longitude"
    }
}
