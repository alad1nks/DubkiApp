package com.app.dubkiapp.ui.stateholders

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.dubkiapp.ui.view.ScheduleFragment
import com.app.dubkiapp.ui.view.ScheduleMoscowFragment

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