package ru.anb.myapplication.features.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.anb.myapplication.R
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentAuthBinding

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding, AuthViewModel>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentAuthBinding =
        { inflater, container ->
            FragmentAuthBinding.inflate(inflater, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.enter.setOnClickListener {

            viewModel.sendAuthRequest(
                login = binding.mailText.text.toString(),
                password = binding.passwordText.text.toString()
            )
        }
        binding.registrationEnter.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registrFragment)
        }
    }
}