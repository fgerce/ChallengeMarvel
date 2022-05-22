package com.intermediait.marvel.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.intermediait.marvel.databinding.FragmentEventsBinding
import com.intermediait.marvel.presentation.EventsViewModel
import com.intermediait.marvel.presentation.ViewState
import com.intermediait.marvel.view.adapter.EventsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : Fragment() {
    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EventsViewModel by viewModels()
    private val eventAdapter = EventsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        binding.rvListEvents.adapter = eventAdapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        viewModel.events.observe(viewLifecycleOwner) {
            eventAdapter.items = it
        }
        viewModel.viewState.observe(viewLifecycleOwner) {
            when(it){
                is ViewState.Failure -> {
                    binding.rvListEvents.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
                ViewState.Loading -> {
                    binding.rvListEvents.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                ViewState.Ready -> {
                    binding.rvListEvents.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getEvents()
    }

}