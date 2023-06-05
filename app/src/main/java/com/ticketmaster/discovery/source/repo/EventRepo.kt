package com.ticketmaster.discovery.source.repo

import com.ticketmaster.discovery.model.Event
import io.reactivex.rxjava3.core.Observable

interface EventRepo {

    fun getEvents(city: String): Observable<List<Event>>
}