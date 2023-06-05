package com.ticketmaster.discovery.source.api

import com.ticketmaster.discovery.model.EventResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface EventApi {

    @GET("v2/events.json")
    fun getEvents(
        @Query("apikey") apiKey: String,
        @Query("city") city: String
    ): Observable<EventResponse>
}