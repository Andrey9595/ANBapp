package ru.anb.myapplication.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B: ViewBinding, V>: Fragment() {
    protected abstract val bindingInflater: (LayoutInflater, ViewGroup?) -> B
    protected var _binding: B? = null
    protected val binding get() = _binding!!
    protected val viewModel: V by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}