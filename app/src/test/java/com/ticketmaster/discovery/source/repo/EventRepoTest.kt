package com.ticketmaster.discovery.source.repo

import com.ticketmaster.discovery.model.City
import com.ticketmaster.discovery.model.Discovery
import com.ticketmaster.discovery.model.Event
import com.ticketmaster.discovery.model.EventResponse
import com.ticketmaster.discovery.model.Place
import com.ticketmaster.discovery.source.api.EventApi
import com.ticketmaster.discovery.source.repo.impl.EventRepoImpl
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EventRepoTest {

    @MockK
    lateinit var eventApi: EventApi

    private lateinit var eventRepo: EventRepo

    @Before
    fun setup() {
        eventRepo = EventRepoImpl(eventApi)
    }

    @Test
    fun testGetEvents() {
        val event = Event("id", "name", "event", "url", "en-us", Place(City("london")), emptyList())
        val events = mutableListOf<Event>()
        events.add(event)
        val embedded = Discovery(events)
        val eventResponse = EventResponse(embedded)
        every { eventApi.getEvents("", "london") } returns Observable.just(eventResponse)

        eventRepo.getEvents("london")

        verify { eventApi.getEvents("", "london") }
    }
}