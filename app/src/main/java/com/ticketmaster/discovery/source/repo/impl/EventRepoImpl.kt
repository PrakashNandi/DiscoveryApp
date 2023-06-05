package com.ticketmaster.discovery.source.repo.impl

import com.ticketmaster.discovery.constants.Constants
import com.ticketmaster.discovery.model.Event
import com.ticketmaster.discovery.source.api.EventApi
import com.ticketmaster.discovery.source.repo.EventRepo
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class EventRepoImpl @Inject constructor(private var eventApi: EventApi) : EventRepo {
    override fun getEvents(city: String): Observable<List<Event>> {
        return eventApi.getEvents(Constants.API_KEY, city)
            .flatMap {
                if (it._embedded == null) {
                    return@flatMap Observable.just(emptyList())
                } else {
                    return@flatMap Observable.just(it._embedded.events)
                }
            }
    }
}