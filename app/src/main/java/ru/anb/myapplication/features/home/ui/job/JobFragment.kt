package ru.anb.myapplication.features.home.ui.job

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentJobBinding
import ru.anb.myapplication.features.home.ui.adapter.JobsAdapter

@AndroidEntryPoint
class JobFragment : BaseFragment<FragmentJobBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentJobBinding =
        { inflater, container ->
            FragmentJobBinding.inflate(inflater, container, false)
        }
    private val viewModel: JobViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = JobsAdapter { viewModel.remove(it) }
        binding.jobsList.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.jobFlow.collect() {
                    adapter.setData(it)
                }
            }
        }
    }
}