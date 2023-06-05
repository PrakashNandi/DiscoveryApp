package com.ticketmaster.discovery.model

import androidx.room.Entity

@Entity(tableName = "image")
data class Image(
    val ratio: String,
    val url: String,
    val width: Int,
    val height: Int,
    val fallback: Boolean
) {
    constructor(): this("", "", 0, 0, false)
}
