package com.app.dubkiapp.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.dubkiapp.databinding.BusItemBinding
import com.app.dubkiapp.domain.Bus

class ScheduleAdapter : ListAdapter<Bus, ScheduleAdapter.ScheduleViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BusItemBinding.inflate(inflater, parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val bus = getItem(position)
        holder.bind(bus)
    }

    class ScheduleViewHolder(
        private val binding: BusItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bus: Bus) {
            with(binding.dayTimeBus) {
                text = bus.dayTimeString
                bus.dayTimeStyleResId?.let { this.setTextAppearance(it) }
            }
            with(binding.inTimeBus) {
                text = bus.inTimeStringResId?.let { context.getString(it, bus.inTimeMinutes, bus.inTimeHours) }
                bus.inTimeStyleResId?.let { this.setTextAppearance(it) }
                bus.inTimeDrawableResId?.let { this.setBackgroundResource(it) }
            }
            with(binding.stationBus) {
                bus.stationStringResId?.let { this.setText(it) }
                bus.stationStyleResId?.let { this.setTextAppearance(it) }
            }
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<Bus>() {
        override fun areItemsTheSame(oldItem: Bus, newItem: Bus): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Bus, newItem: Bus): Boolean {
            return false
        }
    }
}