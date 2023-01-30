package com.app.dubkiapp.ui.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.dubkiapp.databinding.ServiceItemBinding
import com.app.dubkiapp.domain.Service

class ServicesAdapter(
    private val listener: Listener
) : ListAdapter<Service, ServicesAdapter.ServicesViewHolder>(DiffCallBack), View.OnClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ServiceItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return ServicesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        val service = getItem(position)
        holder.bind(service)
    }

    interface Listener {
        fun onClick(service: Service)
    }

    class ServicesViewHolder(
        private val binding: ServiceItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(service: Service) {
            binding.root.tag = service
            binding.nameService.setText(service.stringResourceId)
            binding.imageService.setImageResource(service.imageResourceId)
        }

    }

    companion object DiffCallBack: DiffUtil.ItemCallback<Service>() {
        override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem.stringResourceId == newItem.stringResourceId
        }
    }

    override fun onClick(v: View) {
        val service = v.tag as Service
        listener.onClick(service)
    }
}