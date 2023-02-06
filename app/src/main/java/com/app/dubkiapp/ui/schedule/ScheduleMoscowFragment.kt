package com.app.dubkiapp.ui.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.dubkiapp.databinding.RecyclerScheduleBinding
import javax.inject.Inject

class ScheduleMoscowFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<ScheduleViewModel> { viewModelFactory }
    private var _binding: RecyclerScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var scheduleAdapter: ScheduleAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as com.app.dubkiapp.DubkiApp).appComponent.scheduleComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecyclerScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scheduleAdapter = ScheduleAdapter()
        binding.recyclerSchedule.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerSchedule.adapter = scheduleAdapter
        arguments?.takeIf { it.containsKey("pos") }?.apply {
            when(getInt("pos")) {
                0 -> viewModel.scheduleMoscow.observe(viewLifecycleOwner) {
                    scheduleAdapter.submitList(it)
                }
                else -> viewModel.scheduleDubki.observe(viewLifecycleOwner) {
                    scheduleAdapter.submitList(it)
                }
            }
        }
//        viewModel.schedule.observe(viewLifecycleOwner) {
//            scheduleAdapter.submitList(it)
//        }
//        arguments?.takeIf { it.containsKey("sas") }?.apply {
//        }
    }
}