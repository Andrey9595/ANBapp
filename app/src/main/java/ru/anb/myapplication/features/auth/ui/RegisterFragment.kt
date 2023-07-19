package ru.anb.myapplication.features.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentRegistrBinding

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegistrBinding, RegistrationViewModel>() {
    
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentRegistrBinding =
        { inflater, container ->
            FragmentRegistrBinding.inflate(inflater, container, false)
        }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val inputList = listOf(
            binding.editLogin,
            binding.editName,
            binding.passwordLayout
        )
        
        binding.registration.setOnClickListener {
            
            inputList.forEach { it.isValid() }
//            viewModel.sendRegistrationRequest(
//                login = binding.editLogin.text().toString(),
//                password = binding.editPassword().text.toString(),
//                name = binding.editName.text().toString()
//            )
        }
    }
    
}