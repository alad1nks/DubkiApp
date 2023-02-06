package com.app.dubkiapp.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ScheduleStateAdapter(fragment: ScheduleFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = ScheduleMoscowFragment()
        fragment.arguments = Bundle().apply {
            putInt("pos", position)
        }
        return fragment
    }
}