package ru.anb.myapplication.features.home.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentPostBinding
import ru.anb.myapplication.features.home.ui.adapter.PostsAdapter

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentPostBinding =
        { inflater, container -> FragmentPostBinding.inflate(inflater, container, false) }
    private val viewModel: PostsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PostsAdapter()
        binding.postsList.adapter = adapter
        viewModel.getPagedPosts().onEach { adapter.submitData(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect {
                binding.swipeToRefresh.isRefreshing = it.refresh == LoadState.Loading
            }
        }
        binding.swipeToRefresh.setOnRefreshListener { adapter.refresh() }
    }
}