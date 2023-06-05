package com.ticketmaster.discovery.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ticketmaster.discovery.databinding.FragmentEventsBinding
import com.ticketmaster.discovery.model.Event
import com.ticketmaster.discovery.ui.adapter.EventAdapter
import com.ticketmaster.discovery.ui.viewmodel.EventsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private var events = mutableListOf<Event>()

    private lateinit var eventsViewModel: EventsViewModel

    private lateinit var binding: FragmentEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventAdapter = EventAdapter(requireContext(), events)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = eventAdapter

        eventsViewModel = ViewModelProvider(requireActivity())[EventsViewModel::class.java]
        eventsViewModel.getEvents()

        eventsViewModel.events?.observe(viewLifecycleOwner) {
            with(events) {
                clear()
                addAll(it)
            }
            eventAdapter.notifyDataSetChanged()
        }

        binding.searchEt.doOnTextChanged { text, _, _, _ ->
            if (text.toString().length >= 4) {
                eventsViewModel.getEvents(text.toString())
            }
        }
    }

}