package com.app.dubkiapp.ui.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.dubkiapp.R
import com.app.dubkiapp.domain.Bus

class ScheduleAdapter : ListAdapter<Bus, ScheduleAdapter.ScheduleViewHolder>(DiffCallBack) {

    companion object DiffCallBack: DiffUtil.ItemCallback<Bus>() {
        override fun areItemsTheSame(oldItem: Bus, newItem: Bus): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Bus, newItem: Bus): Boolean {
            return false
        }
    }

    class ScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context: Context = view.context
        val findInTimeBus: TextView = view.findViewById(R.id.inTimeBus)
        val findDayTimeBus: TextView = view.findViewById(R.id.dayTimeBus)
        val findStationBus: TextView = view.findViewById(R.id.stationBus)
//        val findCardBus: View = view.findViewById(R.id.cardBus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ScheduleViewHolder(layoutInflater.inflate(R.layout.bus_item, parent, false))
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            findDayTimeBus.text = item.dayTimeString
            item.dayTimeStyleResId?.let { findDayTimeBus.setTextAppearance(it) }
            findInTimeBus.text = item.inTimeStringResId?.let { context.getString(it, item.inTimeMinutes, item.inTimeHours) }
            item.inTimeStyleResId?.let { findInTimeBus.setTextAppearance(it) }
            item.inTimeDrawableResId?.let { findInTimeBus.setBackgroundResource(it) }
            item.stationStringResId?.let { findStationBus.setText(it) }
            item.stationStyleResId?.let { findStationBus.setTextAppearance(it) }
        }
    }
}