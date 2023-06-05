package com.ticketmaster.discovery.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ticketmaster.discovery.R
import com.ticketmaster.discovery.databinding.MainLayoutBinding
import com.ticketmaster.discovery.ui.fragment.EventFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val eventFragment: EventFragment = EventFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.root_container, eventFragment)
            .commit()
    }
}