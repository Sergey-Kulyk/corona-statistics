package com.sergeykulyk.corona_statistics.ui.dashboard.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.data.dvo.OverviewStatistics
import kotlinx.android.synthetic.main.item_statistics.view.*

class OverviewStatisticsAdapter(var items: List<OverviewStatistics> = listOf()) :
    RecyclerView.Adapter<OverviewStatisticsAdapter.StatisticsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_statistics, parent, false)

        return StatisticsViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class StatisticsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: OverviewStatistics) {
            itemView.statisticsRoot.setBackgroundResource(item.background)
            itemView.statisticsTitle.setText(item.title)
            itemView.number.text = item.number
            itemView.percent.text = item.percentFormatted
        }
    }
}