package ru.anb.myapplication.features.home.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentEventsBinding
import ru.anb.myapplication.features.home.ui.adapter.EventsAdapter

@AndroidEntryPoint
class EventsFragment : BaseFragment<FragmentEventsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentEventsBinding =
        { inflater, container -> FragmentEventsBinding.inflate(inflater, container, false) }
    private val viewModel: EventsViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = EventsAdapter()
        binding.eventsList.adapter = adapter
        viewModel.sendGetAll().onEach { adapter.submitData(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}