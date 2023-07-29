/**
 * Created by Shrey Kharbanda on July 15, 2023
 */

package com.example.android.algoready.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.algoready.databinding.GridViewItemBinding
import com.example.android.algoready.network.VideoProperty

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class PhotoGridAdapter( val onClickListener: OnClickListener ) :
        ListAdapter<VideoProperty, PhotoGridAdapter.VideoPropertyViewHolder>(DiffCallback) {
    /**
     * The VideoPropertyViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [VideoProperty] information.
     */
    class VideoPropertyViewHolder(private var binding: GridViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(videoProperty: VideoProperty) {
            binding.property = videoProperty
            // It forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [VideoProperty]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<VideoProperty>() {
        override fun areItemsTheSame(oldItem: VideoProperty, newItem: VideoProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: VideoProperty, newItem: VideoProperty): Boolean {
            return oldItem.videoId == newItem.videoId
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): VideoPropertyViewHolder {
        return VideoPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: VideoPropertyViewHolder, position: Int) {
        val videoProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(videoProperty)
        }
        holder.bind(videoProperty)
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [VideoProperty]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [VideoProperty]
     */
    class OnClickListener(val clickListener: (videoProperty:VideoProperty) -> Unit) {
        fun onClick(videoProperty:VideoProperty) = clickListener(videoProperty)
    }
}
