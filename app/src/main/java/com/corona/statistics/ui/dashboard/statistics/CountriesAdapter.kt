package com.corona.statistics.ui.dashboard.statistics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.corona.statistics.R
import com.corona.statistics.data.dvo.CountryStatisticsDvo
import com.corona.statistics.getAppContext
import kotlinx.android.synthetic.main.item_country.view.*
import kotlinx.android.synthetic.main.item_worldwide.view.*

class CountriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var countries = listOf<CountryStatisticsDvo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)

        return if (viewType == R.layout.item_worldwide) {
            WorldwideViewHolder(itemView)
        } else {
            CountryViewHolder(itemView)
        }
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            (holder as WorldwideViewHolder).bind(countries[position])
        } else {
            (holder as CountryViewHolder).bind(countries[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            R.layout.item_worldwide
        } else {
            R.layout.item_country
        }
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CountryStatisticsDvo) {

            itemView.countryRoot.background.alpha = 255

            if (adapterPosition % 2 != 0) {
                itemView.countryRoot.setBackgroundResource(R.drawable.bg_country)
                if (adapterPosition >= 3) {
                    itemView.countryRoot.background.alpha = 176
                }
            } else {
                itemView.countryRoot.setBackgroundColor(
                    ContextCompat.getColor(
                        getAppContext(),
                        android.R.color.transparent
                    )
                )
            }

            Glide.with(getAppContext())
                .load(item.icon)
                .placeholder(R.drawable.ic_positional)
                .optionalCircleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.icon)

            itemView.name.text = item.name
            itemView.confirmed.text = item.confirmed
            itemView.recovered.text = item.recovered
            itemView.deaths.text = item.deaths
        }
    }

    inner class WorldwideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CountryStatisticsDvo) {
            itemView.worldwideConfirmed.text = item.confirmed
            itemView.worldwideRecovered.text = item.recovered
            itemView.worldwideDeaths.text = item.deaths
        }
    }
}