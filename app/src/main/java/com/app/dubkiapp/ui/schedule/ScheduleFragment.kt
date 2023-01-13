package com.app.dubkiapp.ui.schedule

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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.dubkiapp.R
import com.app.dubkiapp.databinding.FragmentScheduleBinding
import com.app.dubkiapp.preferences.AppPreference
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import javax.inject.Inject


class ScheduleFragment : Fragment(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ScheduleViewModel> { viewModelFactory }

    private var _binding: FragmentScheduleBinding? = null

    private val binding get() = _binding!!

    private lateinit var tabLayout: TabLayout


    private lateinit var scheduleTodayMoscowAllAdapter: ScheduleAdapter
    private lateinit var scheduleTodayMoscowOdintsovoAdapter: ScheduleAdapter
    private lateinit var scheduleTodayMoscowSlavyankaAdapter: ScheduleAdapter
    private lateinit var scheduleTodayMoscowMolodezhnayaAdapter: ScheduleAdapter
    private lateinit var scheduleTomorrowMoscowAllAdapter: ScheduleAdapter
    private lateinit var scheduleTomorrowMoscowOdintsovoAdapter: ScheduleAdapter
    private lateinit var scheduleTomorrowMoscowSlavyankaAdapter: ScheduleAdapter
    private lateinit var scheduleTomorrowMoscowMolodezhnayaAdapter: ScheduleAdapter
    private lateinit var scheduleWeekdayMoscowAllAdapter: ScheduleAdapter
    private lateinit var scheduleWeekdayMoscowOdintsovoAdapter: ScheduleAdapter
    private lateinit var scheduleWeekdayMoscowSlavyankaAdapter: ScheduleAdapter
    private lateinit var scheduleWeekdayMoscowMolodezhnayaAdapter: ScheduleAdapter
    private lateinit var scheduleSaturdayMoscowAllAdapter: ScheduleAdapter
    private lateinit var scheduleSaturdayMoscowOdintsovoAdapter: ScheduleAdapter
    private lateinit var scheduleSaturdayMoscowSlavyankaAdapter: ScheduleAdapter
    private lateinit var scheduleSaturdayMoscowMolodezhnayaAdapter: ScheduleAdapter
    private lateinit var scheduleSundayMoscowAllAdapter: ScheduleAdapter
    private lateinit var scheduleSundayMoscowOdintsovoAdapter: ScheduleAdapter
    private lateinit var scheduleSundayMoscowSlavyankaAdapter: ScheduleAdapter
    private lateinit var scheduleSundayMoscowMolodezhnayaAdapter: ScheduleAdapter

    private lateinit var scheduleTodayDubkiAllAdapter: ScheduleAdapter
    private lateinit var scheduleTodayDubkiOdintsovoAdapter: ScheduleAdapter
    private lateinit var scheduleTodayDubkiSlavyankaAdapter: ScheduleAdapter
    private lateinit var scheduleTodayDubkiMolodezhnayaAdapter: ScheduleAdapter
    private lateinit var scheduleTomorrowDubkiAllAdapter: ScheduleAdapter
    private lateinit var scheduleTomorrowDubkiOdintsovoAdapter: ScheduleAdapter
    private lateinit var scheduleTomorrowDubkiSlavyankaAdapter: ScheduleAdapter
    private lateinit var scheduleTomorrowDubkiMolodezhnayaAdapter: ScheduleAdapter
    private lateinit var scheduleWeekdayDubkiAllAdapter: ScheduleAdapter
    private lateinit var scheduleWeekdayDubkiOdintsovoAdapter: ScheduleAdapter
    private lateinit var scheduleWeekdayDubkiSlavyankaAdapter: ScheduleAdapter
    private lateinit var scheduleWeekdayDubkiMolodezhnayaAdapter: ScheduleAdapter
    private lateinit var scheduleSaturdayDubkiAllAdapter: ScheduleAdapter
    private lateinit var scheduleSaturdayDubkiOdintsovoAdapter: ScheduleAdapter
    private lateinit var scheduleSaturdayDubkiSlavyankaAdapter: ScheduleAdapter
    private lateinit var scheduleSaturdayDubkiMolodezhnayaAdapter: ScheduleAdapter
    private lateinit var scheduleSundayDubkiAllAdapter: ScheduleAdapter
    private lateinit var scheduleSundayDubkiOdintsovoAdapter: ScheduleAdapter
    private lateinit var scheduleSundayDubkiSlavyankaAdapter: ScheduleAdapter
    private lateinit var scheduleSundayDubkiMolodezhnayaAdapter: ScheduleAdapter


    private var dayDirectionStation = MutableLiveData<List<Char>>()
    private var day = '-'
    private var station = '-'
    private var direction = 'm'
    private var start = true

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


        var nextTodayMoscowAllBus = 0
        var nextTodayMoscowOdintsovoBus = 0
        var nextTodayMoscowSlavyankaBus = 0
        var nextTodayMoscowMolodezhnayaBus = 0

        var nextTodayDubkiAllBus = 0
        var nextTodayDubkiOdintsovoBus = 0
        var nextTodayDubkiSlavyankaBus = 0
        var nextTodayDubkiMolodezhnayaBus = 0


        scheduleTodayMoscowAllAdapter = ScheduleAdapter()
        scheduleTodayMoscowOdintsovoAdapter = ScheduleAdapter()
        scheduleTodayMoscowSlavyankaAdapter = ScheduleAdapter()
        scheduleTodayMoscowMolodezhnayaAdapter = ScheduleAdapter()
        scheduleTomorrowMoscowAllAdapter = ScheduleAdapter()
        scheduleTomorrowMoscowOdintsovoAdapter = ScheduleAdapter()
        scheduleTomorrowMoscowSlavyankaAdapter = ScheduleAdapter()
        scheduleTomorrowMoscowMolodezhnayaAdapter = ScheduleAdapter()
        scheduleWeekdayMoscowAllAdapter = ScheduleAdapter()
        scheduleWeekdayMoscowOdintsovoAdapter = ScheduleAdapter()
        scheduleWeekdayMoscowSlavyankaAdapter = ScheduleAdapter()
        scheduleWeekdayMoscowMolodezhnayaAdapter = ScheduleAdapter()
        scheduleSaturdayMoscowAllAdapter = ScheduleAdapter()
        scheduleSaturdayMoscowOdintsovoAdapter = ScheduleAdapter()
        scheduleSaturdayMoscowSlavyankaAdapter = ScheduleAdapter()
        scheduleSaturdayMoscowMolodezhnayaAdapter = ScheduleAdapter()
        scheduleSundayMoscowAllAdapter = ScheduleAdapter()
        scheduleSundayMoscowOdintsovoAdapter = ScheduleAdapter()
        scheduleSundayMoscowSlavyankaAdapter = ScheduleAdapter()
        scheduleSundayMoscowMolodezhnayaAdapter = ScheduleAdapter()

        scheduleTodayDubkiAllAdapter = ScheduleAdapter()
        scheduleTodayDubkiOdintsovoAdapter = ScheduleAdapter()
        scheduleTodayDubkiSlavyankaAdapter = ScheduleAdapter()
        scheduleTodayDubkiMolodezhnayaAdapter = ScheduleAdapter()
        scheduleTomorrowDubkiAllAdapter = ScheduleAdapter()
        scheduleTomorrowDubkiOdintsovoAdapter = ScheduleAdapter()
        scheduleTomorrowDubkiSlavyankaAdapter = ScheduleAdapter()
        scheduleTomorrowDubkiMolodezhnayaAdapter = ScheduleAdapter()
        scheduleWeekdayDubkiAllAdapter = ScheduleAdapter()
        scheduleWeekdayDubkiOdintsovoAdapter = ScheduleAdapter()
        scheduleWeekdayDubkiSlavyankaAdapter = ScheduleAdapter()
        scheduleWeekdayDubkiMolodezhnayaAdapter = ScheduleAdapter()
        scheduleSaturdayDubkiAllAdapter = ScheduleAdapter()
        scheduleSaturdayDubkiOdintsovoAdapter = ScheduleAdapter()
        scheduleSaturdayDubkiSlavyankaAdapter = ScheduleAdapter()
        scheduleSaturdayDubkiMolodezhnayaAdapter = ScheduleAdapter()
        scheduleSundayDubkiAllAdapter = ScheduleAdapter()
        scheduleSundayDubkiOdintsovoAdapter = ScheduleAdapter()
        scheduleSundayDubkiSlavyankaAdapter = ScheduleAdapter()
        scheduleSundayDubkiMolodezhnayaAdapter = ScheduleAdapter()


        val onTabSelectedListener: OnTabSelectedListener = object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position) {
                    0 -> {
                        direction = 'm'
                        dayDirectionStation.value = listOf(day, direction, station)
                    }
                    else -> {
                        direction = 'd'
                        dayDirectionStation.value = listOf(day, direction, station)
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {
                when(tab.position) {
                    0 -> {
                        //(binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextMoscowBus)
                    }
                    else -> {
                        //(binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextDubkiBus)
                    }
                }
            }
        }

        binding.recyclerSchedule.adapter = scheduleTodayMoscowAllAdapter
        binding.recyclerSchedule.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


        viewModel.scheduleTodayMoscowAll.observe(viewLifecycleOwner) {
            scheduleTodayMoscowAllAdapter.submitList(it.first)
            nextTodayMoscowAllBus = it.second
            if (start) {
                (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextTodayMoscowAllBus)
                Log.d(TAG, "nextMoscowBus $nextTodayMoscowAllBus")
                start = false
            }
            Log.d(TAG, "hereResult: ${it.first}")
        }
        viewModel.scheduleTodayMoscowOdintsovo.observe(viewLifecycleOwner) {
            scheduleTodayMoscowOdintsovoAdapter.submitList(it.first)
            nextTodayMoscowOdintsovoBus = it.second
            Log.d(TAG, "hereResult: ${it.first}")
        }
        viewModel.scheduleTodayMoscowSlavyanka.observe(viewLifecycleOwner) {
            scheduleTodayMoscowSlavyankaAdapter.submitList(it.first)
            nextTodayMoscowSlavyankaBus = it.second
            Log.d(TAG, "hereResult: ${it.first}")
        }
        viewModel.scheduleTodayMoscowMolodezhnaya.observe(viewLifecycleOwner) {
            scheduleTodayMoscowMolodezhnayaAdapter.submitList(it.first)
            nextTodayMoscowMolodezhnayaBus = it.second
            Log.d(TAG, "hereResult: ${it.first}")
        }

        viewModel.scheduleTomorrowMoscowAll.observe(viewLifecycleOwner) {
            scheduleTomorrowMoscowAllAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleTomorrowMoscowOdintsovo.observe(viewLifecycleOwner) {
            scheduleTomorrowMoscowOdintsovoAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleTomorrowMoscowSlavyanka.observe(viewLifecycleOwner) {
            scheduleTomorrowMoscowSlavyankaAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleTomorrowMoscowMolodezhnaya.observe(viewLifecycleOwner) {
            scheduleTomorrowMoscowMolodezhnayaAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }

        viewModel.scheduleWeekdayMoscowAll.observe(viewLifecycleOwner) {
            scheduleWeekdayMoscowAllAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleWeekdayMoscowOdintsovo.observe(viewLifecycleOwner) {
            scheduleWeekdayMoscowOdintsovoAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleWeekdayMoscowSlavyanka.observe(viewLifecycleOwner) {
            scheduleWeekdayMoscowSlavyankaAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleWeekdayMoscowMolodezhnaya.observe(viewLifecycleOwner) {
            scheduleWeekdayMoscowMolodezhnayaAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleSaturdayMoscowAll.observe(viewLifecycleOwner) {
            scheduleSaturdayMoscowAllAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleSaturdayMoscowOdintsovo.observe(viewLifecycleOwner) {
            scheduleSaturdayMoscowOdintsovoAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleSaturdayMoscowSlavyanka.observe(viewLifecycleOwner) {
            scheduleSaturdayMoscowSlavyankaAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleSaturdayMoscowMolodezhnaya.observe(viewLifecycleOwner) {
            scheduleSaturdayMoscowMolodezhnayaAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleSundayMoscowAll.observe(viewLifecycleOwner) {
            scheduleSundayMoscowAllAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleSundayMoscowOdintsovo.observe(viewLifecycleOwner) {
            scheduleSundayMoscowOdintsovoAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleSundayMoscowSlavyanka.observe(viewLifecycleOwner) {
            scheduleSundayMoscowSlavyankaAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleSundayMoscowMolodezhnaya.observe(viewLifecycleOwner) {
            scheduleSundayMoscowMolodezhnayaAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }


        viewModel.scheduleTodayDubkiAll.observe(viewLifecycleOwner) {
            scheduleTodayDubkiAllAdapter.submitList(it.first)
            nextTodayDubkiAllBus = it.second
            Log.d(TAG, "hereResult: ${it.first}")
        }
        viewModel.scheduleTodayDubkiOdintsovo.observe(viewLifecycleOwner) {
            scheduleTodayDubkiOdintsovoAdapter.submitList(it.first)
            nextTodayDubkiOdintsovoBus = it.second
            Log.d(TAG, "hereResult: ${it.first}")
        }
        viewModel.scheduleTodayDubkiSlavyanka.observe(viewLifecycleOwner) {
            scheduleTodayDubkiSlavyankaAdapter.submitList(it.first)
            nextTodayDubkiSlavyankaBus = it.second
            Log.d(TAG, "hereResult: ${it.first}")
        }
        viewModel.scheduleTodayDubkiMolodezhnaya.observe(viewLifecycleOwner) {
            scheduleTodayDubkiMolodezhnayaAdapter.submitList(it.first)
            nextTodayDubkiMolodezhnayaBus = it.second
            Log.d(TAG, "hereResult: ${it.first}")
        }

        viewModel.scheduleTomorrowDubkiAll.observe(viewLifecycleOwner) {
            scheduleTomorrowDubkiAllAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleTomorrowDubkiOdintsovo.observe(viewLifecycleOwner) {
            scheduleTomorrowDubkiOdintsovoAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleTomorrowDubkiSlavyanka.observe(viewLifecycleOwner) {
            scheduleTomorrowDubkiSlavyankaAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleTomorrowDubkiMolodezhnaya.observe(viewLifecycleOwner) {
            scheduleTomorrowDubkiMolodezhnayaAdapter.submitList(it)
            Log.d(TAG, "hereResult: $it")
        }
        viewModel.scheduleWeekdayDubkiAll.observe(viewLifecycleOwner) {
            scheduleWeekdayDubkiAllAdapter.submitList(it)
        }
        viewModel.scheduleWeekdayDubkiOdintsovo.observe(viewLifecycleOwner) {
            scheduleWeekdayDubkiOdintsovoAdapter.submitList(it)
        }
        viewModel.scheduleWeekdayDubkiSlavyanka.observe(viewLifecycleOwner) {
            scheduleWeekdayDubkiSlavyankaAdapter.submitList(it)
        }
        viewModel.scheduleWeekdayDubkiMolodezhnaya.observe(viewLifecycleOwner) {
            scheduleWeekdayDubkiMolodezhnayaAdapter.submitList(it)
        }
        viewModel.scheduleSaturdayDubkiAll.observe(viewLifecycleOwner) {
            scheduleSaturdayDubkiAllAdapter.submitList(it)
        }
        viewModel.scheduleSaturdayDubkiOdintsovo.observe(viewLifecycleOwner) {
            scheduleSaturdayDubkiOdintsovoAdapter.submitList(it)
        }
        viewModel.scheduleSaturdayDubkiSlavyanka.observe(viewLifecycleOwner) {
            scheduleSaturdayDubkiSlavyankaAdapter.submitList(it)
        }
        viewModel.scheduleSaturdayDubkiMolodezhnaya.observe(viewLifecycleOwner) {
            scheduleSaturdayDubkiMolodezhnayaAdapter.submitList(it)
        }
        viewModel.scheduleSundayDubkiAll.observe(viewLifecycleOwner) {
            scheduleSundayDubkiAllAdapter.submitList(it)
        }
        viewModel.scheduleSundayDubkiOdintsovo.observe(viewLifecycleOwner) {
            scheduleSundayDubkiOdintsovoAdapter.submitList(it)
        }
        viewModel.scheduleSundayDubkiSlavyanka.observe(viewLifecycleOwner) {
            scheduleSundayDubkiSlavyankaAdapter.submitList(it)
        }
        viewModel.scheduleSundayDubkiMolodezhnaya.observe(viewLifecycleOwner) {
            scheduleSundayDubkiMolodezhnayaAdapter.submitList(it)
        }


        dayDirectionStation.observe(viewLifecycleOwner) {
            it?.let {
                when(day) {
                    'c' -> {
                        when(direction) {
                            'm' -> {
                                when(station) {
                                    'a' -> {
                                        binding.recyclerSchedule.adapter = scheduleTodayMoscowAllAdapter
                                        (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextTodayMoscowAllBus)
                                    }
                                    'm' -> {
                                        binding.recyclerSchedule.adapter = scheduleTodayMoscowMolodezhnayaAdapter
                                        (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextTodayMoscowMolodezhnayaBus)
                                    }
                                    's' -> {
                                        binding.recyclerSchedule.adapter = scheduleTodayMoscowSlavyankaAdapter
                                        (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextTodayMoscowSlavyankaBus)
                                    }
                                    'o' -> {
                                        binding.recyclerSchedule.adapter = scheduleTodayMoscowOdintsovoAdapter
                                        (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextTodayMoscowOdintsovoBus)
                                    }
                                }
                            }
                            'd' -> {
                                when(station) {
                                    'a' -> {
                                        binding.recyclerSchedule.adapter = scheduleTodayDubkiAllAdapter
                                        (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextTodayDubkiAllBus)
                                    }
                                    'm' -> {
                                        binding.recyclerSchedule.adapter = scheduleTodayDubkiMolodezhnayaAdapter
                                        (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextTodayDubkiMolodezhnayaBus)
                                    }
                                    's' -> {
                                        binding.recyclerSchedule.adapter = scheduleTodayDubkiSlavyankaAdapter
                                        (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextTodayDubkiSlavyankaBus)
                                    }
                                    'o' -> {
                                        binding.recyclerSchedule.adapter = scheduleTodayDubkiOdintsovoAdapter
                                        (binding.recyclerSchedule.layoutManager as LinearLayoutManager).scrollToPosition(nextTodayDubkiOdintsovoBus)
                                    }
                                }
                            }
                        }
                    }
                    't' -> {
                        when(direction) {
                            'm' -> {
                                when(station) {
                                    'a' -> {
                                        binding.recyclerSchedule.adapter = scheduleTomorrowMoscowAllAdapter
                                    }
                                    'm' -> {
                                        binding.recyclerSchedule.adapter = scheduleTomorrowMoscowMolodezhnayaAdapter
                                    }
                                    's' -> {
                                        binding.recyclerSchedule.adapter = scheduleTomorrowMoscowSlavyankaAdapter
                                    }
                                    'o' -> {
                                        binding.recyclerSchedule.adapter = scheduleTomorrowMoscowOdintsovoAdapter
                                    }
                                }
                            }
                            'd' -> {
                                when(station) {
                                    'a' -> {
                                        binding.recyclerSchedule.adapter = scheduleTomorrowDubkiAllAdapter
                                    }
                                    'm' -> {
                                        binding.recyclerSchedule.adapter = scheduleTomorrowDubkiMolodezhnayaAdapter
                                    }
                                    's' -> {
                                        binding.recyclerSchedule.adapter = scheduleTomorrowDubkiSlavyankaAdapter
                                    }
                                    'o' -> {
                                        binding.recyclerSchedule.adapter = scheduleTomorrowDubkiOdintsovoAdapter
                                    }
                                }
                            }
                        }
                    }
                    'w' -> {
                        when(direction) {
                            'm' -> {
                                when(station) {
                                    'a' -> {
                                        binding.recyclerSchedule.adapter = scheduleWeekdayMoscowAllAdapter
                                    }
                                    'm' -> {
                                        binding.recyclerSchedule.adapter = scheduleWeekdayMoscowMolodezhnayaAdapter
                                    }
                                    's' -> {
                                        binding.recyclerSchedule.adapter = scheduleWeekdayMoscowSlavyankaAdapter
                                    }
                                    'o' -> {
                                        binding.recyclerSchedule.adapter = scheduleWeekdayMoscowOdintsovoAdapter
                                    }
                                }
                            }
                            'd' -> {
                                when(station) {
                                    'a' -> {
                                        binding.recyclerSchedule.adapter = scheduleWeekdayDubkiAllAdapter
                                    }
                                    'm' -> {
                                        binding.recyclerSchedule.adapter = scheduleWeekdayDubkiMolodezhnayaAdapter
                                    }
                                    's' -> {
                                        binding.recyclerSchedule.adapter = scheduleWeekdayDubkiSlavyankaAdapter
                                    }
                                    'o' -> {
                                        binding.recyclerSchedule.adapter = scheduleWeekdayDubkiOdintsovoAdapter
                                    }

                                }
                            }
                        }
                    }
                    's' -> {
                        when(direction) {
                            'm' -> {
                                when(station) {
                                    'a' -> {
                                        binding.recyclerSchedule.adapter = scheduleSaturdayMoscowAllAdapter
                                    }
                                    'm' -> {
                                        binding.recyclerSchedule.adapter = scheduleSaturdayMoscowMolodezhnayaAdapter
                                    }
                                    's' -> {
                                        binding.recyclerSchedule.adapter = scheduleSaturdayMoscowSlavyankaAdapter
                                    }
                                    'o' -> {
                                        binding.recyclerSchedule.adapter = scheduleSaturdayMoscowOdintsovoAdapter
                                    }
                                }
                            }
                            'd' -> {
                                when(station) {
                                    'a' -> {
                                        binding.recyclerSchedule.adapter = scheduleSaturdayDubkiAllAdapter
                                    }
                                    'm' -> {
                                        binding.recyclerSchedule.adapter = scheduleSaturdayDubkiMolodezhnayaAdapter
                                    }
                                    's' -> {
                                        binding.recyclerSchedule.adapter = scheduleSaturdayDubkiSlavyankaAdapter
                                    }
                                    'o' -> {
                                        binding.recyclerSchedule.adapter = scheduleSaturdayDubkiOdintsovoAdapter
                                    }
                                }
                            }
                        }
                    }
                    'n' -> {
                        when(direction) {
                            'm' -> {
                                when(station) {
                                    'a' -> {
                                        binding.recyclerSchedule.adapter = scheduleSundayMoscowAllAdapter
                                    }
                                    'm' -> {
                                        binding.recyclerSchedule.adapter = scheduleSundayMoscowMolodezhnayaAdapter
                                    }
                                    's' -> {
                                        binding.recyclerSchedule.adapter = scheduleSundayMoscowSlavyankaAdapter
                                    }
                                    'o' -> {
                                        binding.recyclerSchedule.adapter = scheduleSundayMoscowOdintsovoAdapter
                                    }
                                }
                            }
                            'd' -> {
                                when(station) {
                                    'a' -> {
                                        binding.recyclerSchedule.adapter = scheduleSundayDubkiAllAdapter
                                    }
                                    'm' -> {
                                        binding.recyclerSchedule.adapter = scheduleSundayDubkiMolodezhnayaAdapter
                                    }
                                    's' -> {
                                        binding.recyclerSchedule.adapter = scheduleSundayDubkiSlavyankaAdapter
                                    }
                                    'o' -> {
                                        binding.recyclerSchedule.adapter = scheduleSundayDubkiOdintsovoAdapter
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        tabLayout.addOnTabSelectedListener(onTabSelectedListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG, "ONDESTROY")
        direction = 'm'
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        when(parent.getItemAtPosition(pos)) {
            "Все станции" -> {
                station = 'a'
            }
            "Молодежная" -> {
                station = 'm'
            }
            "Славянский б-р" -> {
                station = 's'
            }
            "Одинцово" -> {
                station = 'o'
            }

            "Сегодня" -> {
                day = 'c'
            }
            "Завтра" -> {
                day = 't'
            }
            "Будни" -> {
                day = 'w'
            }
            "Суббота" -> {
                day = 's'
            }
            "Воскресенье" -> {
                day = 'n'
            }
        }

        if (day != '-' && station != '-') {
            dayDirectionStation.value = listOf(day, direction, station)
            Log.d(TAG, "$day, $direction, $station")
        }

//        when(day) {
//            "cur" -> {
//                viewModel.getScheduleTodayAll()
//                Log.d(TAG, "cur")
//            }
//            "wkd" -> {
//                viewModel.getScheduleWeekdayAll()
//                Log.d(TAG, "wkd")
//            }
//            "std" -> {
//                viewModel.getScheduleSaturdayAll()
//                Log.d(TAG, "std")
//            }
//            "snd" -> {
//                viewModel.getScheduleSundayAll()
//                Log.d(TAG, "snd")
//            }
//        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}

