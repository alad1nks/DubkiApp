package com.example.dubkiapp.ui.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dubkiapp.R
import com.example.dubkiapp.domain.Bus

class ScheduleDubkiAdapter : ListAdapter<Bus, ScheduleDubkiAdapter.ScheduleDubkiViewHolder>(DiffCallBack) {

    companion object DiffCallBack: DiffUtil.ItemCallback<Bus>() {
        override fun areItemsTheSame(oldItem: Bus, newItem: Bus): Boolean {
            return oldItem.inTime == newItem.inTime
        }

        override fun areContentsTheSame(oldItem: Bus, newItem: Bus): Boolean {
            return oldItem.inTime == newItem.inTime
        }
    }

    class ScheduleDubkiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context: Context = view.context
        val findDayTimeBus: TextView = view.findViewById(R.id.dayTimeBus)
        val findInTimeBus: TextView = view.findViewById(R.id.inTimeBus)
        val findStationBus: TextView = view.findViewById(R.id.stationBus)
        val findCardBus: View = view.findViewById(R.id.cardBus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDubkiViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ScheduleDubkiViewHolder(layoutInflater.inflate(R.layout.bus_item, parent, false))
    }

    override fun onBindViewHolder(holder: ScheduleDubkiViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            if (item.inTime!! / 60 > 0) {
                findInTimeBus.text = context.getString(R.string.coming, item.inTime / 60, item.inTime % 60)
                findInTimeBus.setTextAppearance(R.style.TextAppearance_Medium_Intime)
                findInTimeBus.setBackgroundResource(R.drawable.in_time_bus_common_bg)
            } else if (item.inTime > 30) {
                findInTimeBus.text = context.getString(R.string.coming_soon, item.inTime)
                findInTimeBus.setTextAppearance(R.style.TextAppearance_Medium_Intime)
                findInTimeBus.setBackgroundResource(R.drawable.in_time_bus_common_bg)
            } else if (item.inTime >= 0) {
                findInTimeBus.text = context.getString(R.string.coming_soon, item.inTime)
                findInTimeBus.setTextAppearance(R.style.TextAppearance_Medium_Intime_Soon)
                findInTimeBus.setBackgroundResource(R.drawable.in_time_bus_soon_bg)
            } else if (item.inTime / 60 < 0) {
                findInTimeBus.text = context.getString(R.string.gone, -item.inTime / 60, -item.inTime % 60)
                findInTimeBus.setTextAppearance(R.style.TextAppearance_Medium_InTime_Gone)
                findInTimeBus.setBackgroundResource(R.drawable.in_time_bus_common_bg)
            } else {
                findInTimeBus.text = context.getString(R.string.gone_soon, -item.inTime)
                findInTimeBus.setTextAppearance(R.style.TextAppearance_Medium_InTime_Gone)
                findInTimeBus.setBackgroundResource(R.drawable.in_time_bus_common_bg)
            }
            findDayTimeBus.text = item.dayTimeFormat

            when (item.station) {
                "Одинцово" -> {
                    findStationBus.text = "Одинцово"
                    if (item.inTime >= 0) {
                        findStationBus.setTextAppearance(R.style.TextAppearance_Medium_Station_Odintsovo)
                        findDayTimeBus.setTextAppearance(R.style.TextAppearance_Medium_DayTime_Odintsovo)
                    } else {
                        findStationBus.setTextAppearance(R.style.TextAppearance_Medium_Station_Gone)
                        findDayTimeBus.setTextAppearance(R.style.TextAppearance_Medium_DayTime_Gone)
                    }
                }
                "Молодежная" -> {
                    findStationBus.text = "Молодежная"
                    if (item.inTime >= 0) {
                        findStationBus.setTextAppearance(R.style.TextAppearance_Medium_Station_Molodyozhnaya)
                        findDayTimeBus.setTextAppearance(R.style.TextAppearance_Medium_DayTime_Molodyozhnaya)
                    } else {
                        findStationBus.setTextAppearance(R.style.TextAppearance_Medium_Station_Gone)
                        findDayTimeBus.setTextAppearance(R.style.TextAppearance_Medium_DayTime_Gone)
                    }
                }
                else -> {
                    findStationBus.text = "Cлавянский б-р"
                    if (item.inTime >= 0) {
                        findStationBus.setTextAppearance(R.style.TextAppearance_Medium_Station_Slavyanka)
                        findDayTimeBus.setTextAppearance(R.style.TextAppearance_Medium_DayTime_Slavyanka)
                    } else {
                        findStationBus.setTextAppearance(R.style.TextAppearance_Medium_Station_Gone)
                        findDayTimeBus.setTextAppearance(R.style.TextAppearance_Medium_DayTime_Gone)
                    }
                }
            }
        }
    }
}

