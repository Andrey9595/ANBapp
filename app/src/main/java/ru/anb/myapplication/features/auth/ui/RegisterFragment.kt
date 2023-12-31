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
import ru.anb.myapplication.databinding.FragmentRegistrBinding

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegistrBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentRegistrBinding =
        { inflater, container ->
            FragmentRegistrBinding.inflate(inflater, container, false)
        }

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputList = listOf(
            binding.signUpLogin,
            binding.signUpName,
            binding.signUpPasswordLayout
        )

        binding.startSignUp.setOnClickListener {

            val allValidation = inputList.map { it.isValid() }

            if (allValidation.all { it }) {

                viewModel.sendRegistrationRequest(
                    login = binding.signUpLogin.text(),
                    password = binding.signUpPasswordLayout.text(),
                    name = binding.signUpName.text()
                )
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registrationState.collect {
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