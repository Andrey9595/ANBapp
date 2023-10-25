package ru.anb.myapplication.features.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentAuthBinding

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentAuthBinding =
        { inflater, container ->
            FragmentAuthBinding.inflate(inflater, container, false)
        }

    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputList = listOf(
            binding.authLogin,
            binding.authPassword
        )

        binding.signIn.setOnClickListener {

            val allValidation = inputList.map { it.isValid() }

            if (allValidation.all { it }) {
                viewModel.sendAuthRequest(
                    login = binding.authLogin.text(),
                    password = binding.authPassword.text()
                )
            }
        }
        binding.navigateToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registrFragment)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.authState.collect {
                    when (it) {
                        is AppLoadState.Loading -> {}
                        is AppLoadState.Success -> {
                            findNavController().navigate(
                                R.id.homeFragment, null, NavOptions.Builder()
                                    .setPopUpTo(
                                        destinationId = R.id.homeFragment,
                                        inclusive = true,
                                    )
                                    .build()
                            )
                        }

                        is AppLoadState.Error -> {
                            Toast.makeText(requireContext(), it.asString(requireContext()), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}