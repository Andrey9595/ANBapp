package ru.anb.myapplication.features.newpost.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentNewPostBinding
import ru.anb.myapplication.features.newpost.domain.model.PostRequest

@AndroidEntryPoint
class NewPostFragment : BaseFragment<FragmentNewPostBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentNewPostBinding =
        { inflater, container -> FragmentNewPostBinding.inflate(inflater, container, false) }

    private val viewModel: NewPostViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newPostState.collect {
                    if (it is AppLoadState.Success) findNavController().popBackStack()
                }
            }
        }

        binding.newPostAppBar.setOnMenuItemClickListener { menuIteem ->

            val editPost = binding.edit.text.toString()

            val createRequest = PostRequest(content = editPost)
            viewModel.createNewPost(createRequest)
            true
        }
    }
}