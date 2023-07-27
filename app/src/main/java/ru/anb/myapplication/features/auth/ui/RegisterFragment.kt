package ru.anb.myapplication.features.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentRegistrBinding

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegistrBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentRegistrBinding =
        { inflater, container ->
            FragmentRegistrBinding.inflate(inflater, container, false)
        }

    private val viewModel: RegistrationViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputList = listOf(
            binding.editLogin,
            binding.editName,
            binding.passwordLayout
        )

        binding.registration.setOnClickListener {

            val allValidation = inputList.map { it.isValid() }

            if (allValidation.all { it }) {

                viewModel.sendRegistrationRequest(
                    login = binding.editLogin.text(),
                    password = binding.passwordLayout.text(),
                    name = binding.editName.text()
                )
            }
        }
    }
}