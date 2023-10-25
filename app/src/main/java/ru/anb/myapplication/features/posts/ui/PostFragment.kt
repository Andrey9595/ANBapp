package ru.anb.myapplication.features.posts.ui

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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentPostBinding

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentPostBinding =
        { inflater, container -> FragmentPostBinding.inflate(inflater, container, false) }
    private val viewModel: PostsViewModel by viewModels()

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
        val adapter = PostsAdapter(PostInteractionListener(viewModel))
        binding.postsList.adapter = adapter
        viewModel.getPagedPosts().onEach {
            adapter.submitData(it)
        }
            .launchIn(viewLifecycleOwner.lifecycleScope)
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect {
                binding.swipeToRefresh.isRefreshing = it.refresh == LoadState.Loading
            }
        }
        binding.swipeToRefresh.setOnRefreshListener { adapter.refresh() }
    }
}