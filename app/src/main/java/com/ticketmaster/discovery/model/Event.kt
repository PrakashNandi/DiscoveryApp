package com.ticketmaster.discovery.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class Event(
    @PrimaryKey
    val id: String,

    val name: String,
    val type: String,
    val url: String,
    val locale: String,
    val place: Place,
    val images: List<Image> = emptyList()
)
