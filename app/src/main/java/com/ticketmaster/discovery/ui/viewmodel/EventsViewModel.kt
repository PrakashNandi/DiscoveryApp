package com.ticketmaster.discovery.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ticketmaster.discovery.model.Event
import com.ticketmaster.discovery.source.repo.EventRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
@SuppressLint("CheckResult")
class EventsViewModel @Inject constructor(var eventRepo: EventRepo) : ViewModel() {

    var events: MutableLiveData<List<Event>>? = MutableLiveData()

    fun getEvents(city: String = "") {
        eventRepo.getEvents(city)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { events?.value = it }
    }
}