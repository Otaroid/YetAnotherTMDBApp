package otaroid.yetanothertmdbapp.presentation.popular_tv_shows.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.robinhood.ticker.TickerUtils
import otaroid.yetanothertmdbapp.R
import otaroid.yetanothertmdbapp.databinding.ItemPopularTvListBinding
import otaroid.yetanothertmdbapp.domain.model.TVShow

class PopularShowsAdapter() :
    RecyclerView.Adapter<PopularShowsAdapter.ShowViewHolder>() {

    inner class ShowViewHolder(val binding: ItemPopularTvListBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<TVShow>() {
        override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val binding =
            ItemPopularTvListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val tvShow = differ.currentList[position]
        holder.apply {
            Glide.with(holder.itemView.context).load(tvShow.posterPath).transition(
                DrawableTransitionOptions.withCrossFade()).into(binding.ivPoster)
            binding.tvShowName.text = tvShow.name
            binding.tvShowAirDate.text = tvShow.firstAirDate
            binding.tvRatingPercent.setCharacterLists(TickerUtils.provideAlphabeticalList())
            binding.tvRatingPercent.text = tvShow.rating.toString()
            binding.pbRating.progress = tvShow.rating
            setOnItemClickListener {
                onItemClickListener?.let { it(tvShow) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((TVShow) -> Unit)? = null

    fun setOnItemClickListener(listener: (TVShow) -> Unit) {
        onItemClickListener = listener
    }
}