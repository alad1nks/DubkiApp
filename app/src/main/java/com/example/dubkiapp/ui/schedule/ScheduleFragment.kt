package com.example.dubkiapp.ui.schedule

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dubkiapp.DubkiApp
import com.example.dubkiapp.R
import com.example.dubkiapp.databinding.FragmentScheduleBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScheduleFragment : Fragment(), AdapterView.OnItemSelectedListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ScheduleViewModel> { viewModelFactory }

    private var _binding: FragmentScheduleBinding? = null

    private val binding get() = _binding!!

    private lateinit var tabLayout: TabLayout

    private var day = ""
    private var station = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as DubkiApp).appComponent.scheduleComponent().create().inject(this)
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


        if (savedInstanceState != null ){
            setCurrentTab(savedInstanceState.getInt("tabState"))
        }
        var nextMoscowBus = 0
        var nextDubkiBus = 0
        val scheduleMoscowAdapter = ScheduleMoscowAdapter()
        val scheduleDubkiAdapter = ScheduleDubkiAdapter()
        val onTabSelectedListener: OnTabSelectedListener = object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position) {
                    0 -> {
                        binding.recyclerSchedule.adapter = scheduleMoscowAdapter
                        (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextMoscowBus)
                    }
                    else -> {
                        binding.recyclerSchedule.adapter = scheduleDubkiAdapter
                        (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextDubkiBus)
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {
                when(tab.position) {
                    0 -> {
                        (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextMoscowBus)
                    }
                    else -> {
                        (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextDubkiBus)
                    }
                }
            }
        }

        binding.recyclerSchedule.adapter = when(savedInstanceState) {
            null -> scheduleMoscowAdapter
            else -> when(savedInstanceState.getInt("tabState")) {
                0 -> scheduleMoscowAdapter
                else -> scheduleDubkiAdapter
            }
        }
        binding.recyclerSchedule.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

//        lifecycleScope.launch {
//            viewModel.getSchedule(day, station)
//        }

        viewModel.scheduleMoscow.observe(viewLifecycleOwner) {
            it?.let {
                scheduleMoscowAdapter.submitList(it.first)
                nextMoscowBus = it.second
                (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextMoscowBus)
                Log.d(TAG, "nextMoscowBus $nextMoscowBus")
            }
        }

        viewModel.scheduleDubki.observe(viewLifecycleOwner) {
            it?.let {
                scheduleDubkiAdapter.submitList(it.first)
                nextDubkiBus = it.second
            }
        }

        tabLayout.addOnTabSelectedListener(onTabSelectedListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("tabState", getSelectedTab())
    }

    private fun getSelectedTab(): Int = tabLayout.selectedTabPosition

    private fun setCurrentTab(state: Int) {
        tabLayout.selectTab(tabLayout.getTabAt(state))
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        when(parent.getItemAtPosition(pos)) {
            "Все станции" -> {
                station = "all"
            }
            "Молодежная" -> {
                station = "mld"
            }
            "Славянский б-р" -> {
                station = "slv"
            }
            "Одинцово" -> {
                station = "odn"
            }

            "Сегодня" -> {
                day = "cur"
            }
            "Завтра" -> {
                day = "tom"
            }
            "Будни" -> {
                day = "wkd"
            }
            "Суббота" -> {
                day = "std"
            }
            "Воскресенье" -> {
                day = "snd"
            }
        }

        if (day != "" && station != "") {
            lifecycleScope.launch {
                viewModel.getSchedule(day, station)
            }
        } else {
            Log.d(TAG, "raptor $day $station")
        }

        Log.d(TAG, "adapter ${parent.getItemAtPosition(pos)}")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}

