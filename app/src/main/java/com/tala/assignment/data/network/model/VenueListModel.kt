package com.tala.assignment.data.network.model

object VenueListModel {
    data class Response(val response: Venues)
    data class Venues(val venues: List<VenueRow>)
    data class VenueRow(val id: String, val name: String, val contact: Contact,
                        val location: Location, val categories: ArrayList<CategoryModel.CategoryRow>)

    data class Contact(val formattedPhone: String)
    data class Location(val address: String, val crossStreet: String, val cc: String,
                        val city: String, val state: String, val formattedAddress: List<String>,
                        val lat: String, val lng: String)
}