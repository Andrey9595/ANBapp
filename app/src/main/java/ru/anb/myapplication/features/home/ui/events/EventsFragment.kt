package ru.anb.myapplication.features.home.ui.events

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentEventsBinding
import ru.anb.myapplication.features.home.ui.adapter.EventsAdapter

@AndroidEntryPoint
class EventsFragment : BaseFragment<FragmentEventsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentEventsBinding =
        { inflater, container -> FragmentEventsBinding.inflate(inflater, container, false) }
    private val viewModel: EventsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = EventsAdapter(EventsInteractionListener(viewModel))
        binding.eventsList.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.sendGetAll().collectLatest {
                Log.d("AAA", it.toString())
                adapter.submitData(it) }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect {
                binding.swipeToRefresh.isRefreshing = it.refresh == LoadState.Loading
            }
        }
        binding.swipeToRefresh.setOnRefreshListener { adapter.refresh() }
    }
}