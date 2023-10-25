package ru.anb.myapplication.features.events.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentEventsBinding

@AndroidEntryPoint
class EventsFragment : BaseFragment<FragmentEventsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentEventsBinding =
        { inflater, container -> FragmentEventsBinding.inflate(inflater, container, false) }
    private val viewModel: EventsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.errorFlow.collect() {
                    if (it is AppLoadState.Error)
                        Toast.makeText(
                            requireContext(),
                            it.asString(requireContext()),
                            Toast.LENGTH_SHORT
                        ).show()
                }
            }
        }
        val adapter = EventsAdapter(EventsInteractionListener(viewModel))
        binding.eventsList.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.sendGetAll().collectLatest {
                adapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect {
                binding.swipeToRefresh.isRefreshing = it.refresh == LoadState.Loading
            }
        }
        binding.swipeToRefresh.setOnRefreshListener { adapter.refresh() }
    }
}