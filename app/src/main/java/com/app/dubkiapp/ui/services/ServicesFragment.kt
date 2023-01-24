package com.app.dubkiapp.ui.services

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.dubkiapp.databinding.FragmentServicesBinding
import com.app.dubkiapp.domain.Service
import javax.inject.Inject

class ServicesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ServicesViewModel> { viewModelFactory }

    private var _binding: FragmentServicesBinding? = null

    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as com.app.dubkiapp.DubkiApp).appComponent.servicesComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServicesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val servicesAdapter = ServicesAdapter(object : ServicesAdapter.Listener {
            override fun onClick(service: Service) {
                service.onClick()
            }
        })
        binding.recyclerServices.adapter = servicesAdapter
        binding.recyclerServices.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        viewModel.services.observe(viewLifecycleOwner) {
            servicesAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}