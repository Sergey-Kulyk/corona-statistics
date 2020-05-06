package com.sergeykulyk.corona_statistics.ui.dashboard.preventation.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.viewanimator.ViewAnimator
import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.ui.dashboard.preventation.Preventation
import kotlinx.android.synthetic.main.item_preventation.view.*

class PreventationAdapter(private val slides: List<Preventation>) :
    RecyclerView.Adapter<PreventationAdapter.PreventationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreventationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_preventation, parent, false)
        return PreventationViewHolder(itemView)
    }

    override fun getItemCount() = slides.size

    override fun onBindViewHolder(holder: PreventationViewHolder, position: Int) {
        holder.bind(slides[position])
    }

    inner class PreventationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Preventation) {
            itemView.preventationIcon.setOnClickListener {
                ViewAnimator.animate(it)
                    .tada()
                    .duration(1000)
                    .accelerate()
                    .start()
            }
            itemView.preventationIcon.setImageResource(item.icon)
            itemView.preventationTitle.setText(item.title)
            itemView.preventationDescription.setText(item.description)
            itemView.playButton.setOnClickListener {
                animatePlayButton(it)
            }

            itemView.learMoreButton.setOnClickListener {
                animatePlayButton(it)
            }
        }

        private fun animatePlayButton(it: View?) {
            ViewAnimator.animate(it)
                .scale(0.8f)
                .duration(250)
                .onStop {
                    ViewAnimator.animate(it)
                        .scale(1f)
                        .duration(200)
                        .start()
                }
                .accelerate()
                .start()
        }

    }

}