package ru.anb.myapplication.features.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.anb.myapplication.R
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

        binding.includedAppBar.appBarImage.setOnClickListener {
            if (viewModel.isAuthorized.value)
                showProfileMenu(it)
            else showAuthMenu(it)
        }
    }

    private fun showProfileMenu(view: View) {
        val profileMenu = PopupMenu(requireContext(), view)
        profileMenu.inflate(R.menu.bottom_nav_menu)
        profileMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.profileBtn -> {
                    findNavController().navigate(R.id.profileFragment)
                }

                R.id.logout -> {
                    Toast.makeText(requireContext(), "выход из профиля", Toast.LENGTH_SHORT).show()
                }

            }
            true
        }
        profileMenu.show()
    }

    private fun showAuthMenu(view: View) {
        val menu = PopupMenu(requireContext(), view)
        menu.inflate(R.menu.menu_main)
        menu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.signIn -> {
                    findNavController().navigate(R.id.authFragment)
                }

                R.id.signUp -> {
                    findNavController().navigate(R.id.registrFragment)
                }
            }
            true
        }
        menu.show()
    }

}