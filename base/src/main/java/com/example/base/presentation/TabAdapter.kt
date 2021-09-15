package com.example.base.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

internal class TabAdapter(
    private val items: List<Fragment>,
    private val titles: List<String>,
    childFragmentManager: FragmentManager
) : FragmentStatePagerAdapter(childFragmentManager) {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

}
