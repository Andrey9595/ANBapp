package ru.anb.myapplication.features.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentProfileBinding =
        { inflater, container ->
            FragmentProfileBinding.inflate(inflater, container, false)
        }

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userState.collect {
                    when (it) {
                        is AppLoadState.Loading -> {}
                        is AppLoadState.Success -> {
                            binding.userLogin.text = it.data.login
                            binding.userName.text = it.data.name
                            Glide.with(binding.userAvatar)
                                .load(it.data.avatar)
                                .circleCrop()
                                .placeholder(R.drawable.ic_baseline_account_circle_24)
                                .into(binding.userAvatar)
                        }

                        is AppLoadState.Error -> {
                            Toast.makeText(requireContext(), it.asString(requireContext()), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}