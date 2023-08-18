package ru.anb.myapplication.features.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentHomeBinding
import ru.anb.myapplication.features.home.ui.adapter.HomeViewPagerAdapter

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentHomeBinding =
        { inflater, container ->
            FragmentHomeBinding.inflate(inflater, container, false)
        }

    private val viewModel: HomeViewModel by lazy { initViewModel() }

    private val fragTitles = listOf(
        "Записи",
        "Мероприятия"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.text = fragTitles[pos]
        }.attach()
    }
}