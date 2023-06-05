package com.ticketmaster.discovery.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.ticketmaster.discovery.model.City
import com.ticketmaster.discovery.model.Event
import com.ticketmaster.discovery.model.Place
import com.ticketmaster.discovery.source.repo.EventRepo
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EventsViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var eventRepo: EventRepo

    private lateinit var eventViewModel: EventsViewModel

    @Before
    fun setup() {
        eventViewModel = EventsViewModel(eventRepo)
    }

    @Test
    fun testGetEvents() {
        // Given
        val event = Event("id", "name", "event", "url", "en-us", Place(City("london")), emptyList())
        val events = mutableListOf<Event>()
        events.add(event)
        every { eventRepo.getEvents("london") } returns Observable.just(events)

        // When
        eventViewModel?.getEvents("london")
        val resultEvents: MutableLiveData<List<Event>>? = eventViewModel?.events

        // Then
        verify { eventRepo.getEvents("london") }
        Assert.assertEquals(1, resultEvents?.value?.size)
    }
}