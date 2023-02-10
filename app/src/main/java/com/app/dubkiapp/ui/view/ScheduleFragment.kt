package com.app.dubkiapp.ui.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.app.dubkiapp.MainActivity
import com.app.dubkiapp.R
import com.app.dubkiapp.databinding.FragmentScheduleBinding
import com.app.dubkiapp.ui.stateholders.ScheduleStateAdapter
import com.app.dubkiapp.ui.stateholders.ScheduleViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject


class ScheduleFragment : Fragment(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<ScheduleViewModel>({ activity as MainActivity }) { viewModelFactory }
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var scheduleStateAdapter: ScheduleStateAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as com.app.dubkiapp.DubkiApp).appComponent.scheduleComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = binding.tabs
        scheduleStateAdapter = ScheduleStateAdapter(this)
        viewPager = binding.viewPagerSchedule
        viewPager.adapter = scheduleStateAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(
                when(position) {
                    0 -> R.string.title_to_moscow
                    else -> R.string.title_to_dubki
                }
            )
        }.attach()

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.stations_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.stationSpinner.adapter = adapter
            }
        }
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.days_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.daySpinner.adapter = adapter
            }
        }
        binding.stationSpinner.onItemSelectedListener = this
        binding.daySpinner.onItemSelectedListener = this
//        val onTabSelectedListener: OnTabSelectedListener = object : OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {}
//            override fun onTabUnselected(tab: TabLayout.Tab) {}
//            override fun onTabReselected(tab: TabLayout.Tab) {}
//        }
//
//        tabLayout.addOnTabSelectedListener(onTabSelectedListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("TAG", "ONDESTROY")
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val spinner = parent as Spinner

        when(spinner.id) {
            R.id.station_spinner -> {
                viewModel.setStation(pos)
            }
            else -> {
                viewModel.setDay(pos)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}

