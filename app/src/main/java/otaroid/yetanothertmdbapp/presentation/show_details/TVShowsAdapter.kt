package otaroid.yetanothertmdbapp.presentation.show_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.robinhood.ticker.TickerUtils
import otaroid.yetanothertmdbapp.databinding.ItemPopularTvListBinding
import otaroid.yetanothertmdbapp.databinding.ItemSimilarTvListBinding
import otaroid.yetanothertmdbapp.domain.model.TVShow

class SimilarShowsAdapter() :
    PagingDataAdapter<TVShow, SimilarShowsAdapter.SimilarShowsViewHolder>(SHOW_DIFFER) {

    inner class SimilarShowsViewHolder(val binding: ItemSimilarTvListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShow) {
            binding.apply {
                Glide.with(itemView.context).load(tvShow.posterPath).transition(
                    DrawableTransitionOptions.withCrossFade()
                ).into(binding.ivPoster)
                tvShowName.text = tvShow.name
                tvShowAirDate.text = tvShow.firstAirDate
                tvRatingPercent.setCharacterLists(TickerUtils.provideAlphabeticalList())
                tvRatingPercent.text = tvShow.rating.toString()
                pbRating.progress = tvShow.rating
                ivPoster.setOnClickListener {
                    onItemClickListener?.let {
                        it(tvShow)
                    }
                }
                rootView.setOnClickListener {
                    onItemClickListener?.let {
                        it(tvShow)
                    }
                }
//                itemView.rootView.setOnClickListener {
//                    Timber.d("კლიკი")
//                    onItemClickListener?.let {
//                        it(tvShow)
//                    }
//                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarShowsViewHolder {
        val binding =
            ItemSimilarTvListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimilarShowsViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: SimilarShowsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
            holder.itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(currentItem)
                }
            }
        }
    }


    private var onItemClickListener: ((TVShow) -> Unit)? = null

    fun setOnItemClickListener(listener: (TVShow) -> Unit) {
        onItemClickListener = listener
    }


    companion object {
        private val SHOW_DIFFER = object : DiffUtil.ItemCallback<TVShow>() {
            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem == newItem
            }
        }
    }
}