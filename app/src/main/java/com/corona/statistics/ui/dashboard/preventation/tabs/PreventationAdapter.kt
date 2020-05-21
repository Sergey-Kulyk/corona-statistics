package com.corona.statistics.ui.dashboard.preventation.tabs

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.viewanimator.ViewAnimator
import com.corona.statistics.R
import com.corona.statistics.getAppContext
import com.corona.statistics.getResources
import com.corona.statistics.ui.dashboard.preventation.Preventation
import kotlinx.android.synthetic.main.item_preventation.view.*

class PreventationAdapter(
    private val slides: List<Preventation>
) :
    RecyclerView.Adapter<PreventationAdapter.PreventationViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreventationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_preventation, parent, false)
        return PreventationViewHolder(itemView)
    }

    override fun getItemCount() = slides.size

    override fun onBindViewHolder(holder: PreventationViewHolder, position: Int) {
        holder.bind(slides[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position * 1L
    }

    inner class PreventationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var iconAnimationViewAnimator: ViewAnimator? = null

        fun bind(item: Preventation) {
            iconAnimationViewAnimator?.cancel()

            iconAnimationViewAnimator = ViewAnimator.animate(itemView.preventationIcon)
                .scale(1f, 1.2f, 1f)
                .repeatCount(ViewAnimator.INFINITE)
                .duration(1700)
                .start()

            itemView.preventationIcon.setImageResource(item.icon)
            itemView.preventationTitle.setText(item.title)
            itemView.preventationDescription.setText(item.description)
            itemView.playButton.setOnClickListener {
                animateScaleButton(it) { onPlayClickAction() }
            }
            itemView.learMoreButton.setOnClickListener {
                animateScaleButton(it) { onLearnMoreClickAction() }
            }
        }

        private fun animateScaleButton(it: View?, action: () -> Unit) {
            ViewAnimator.animate(it)
                .scale(1f, 0.8f, 1f)
                .accelerate()
                .duration(500)
                .onStop { action.invoke() }
                .start()
        }

        private fun onPlayClickAction() {
            openLink(R.string.preventation_video_link)
        }

        private fun onLearnMoreClickAction() {
            openLink(R.string.preventation_learn_more_link)
        }

        private fun openLink(linkStringId: Int) {
            val uri = Uri.parse(getResources().getString(linkStringId))
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            getAppContext().startActivity(intent)
        }

    }

}