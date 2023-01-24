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
        val item = getItem(position)

        with(holder.binding) {
            root.tag = item
            nameService.setText(item.stringResourceId)
            imageService.setImageResource(item.imageResourceId)
        }
    }

    interface Listener {
        fun onClick(service: Service)
    }


    class ServicesViewHolder(
        val binding: ServiceItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

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