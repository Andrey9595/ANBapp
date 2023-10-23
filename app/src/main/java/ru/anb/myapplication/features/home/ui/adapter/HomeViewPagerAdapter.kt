package ru.anb.myapplication.features.home.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.anb.myapplication.features.events.ui.EventsFragment
import ru.anb.myapplication.features.home.ui.job.JobFragment
import ru.anb.myapplication.features.posts.ui.PostFragment

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val list: List<Fragment> = listOf(PostFragment(), EventsFragment(), JobFragment())

    override fun getItemCount() = list.size

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}