package otaroid.yetanothertmdbapp.presentation.show_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.robinhood.ticker.TickerUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import otaroid.yetanothertmdbapp.R
import otaroid.yetanothertmdbapp.databinding.FragmentShowDetailsBinding
import otaroid.yetanothertmdbapp.domain.model.TVShowDetails
import otaroid.yetanothertmdbapp.presentation.main_tv_shows.adapter.PopularTVsLoadStateAdapter
import timber.log.Timber
import androidx.recyclerview.widget.DividerItemDecoration


@AndroidEntryPoint
class ShowDetailsFragment : Fragment(R.layout.fragment_show_details) {
    val args: ShowDetailsFragmentArgs by navArgs()
    val viewModel: ShowDetailsViewModel by viewModels()
    private var _binding: FragmentShowDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var tvAdapter: SimilarShowsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivPoster.apply {
            transitionName = args.tvShow.posterPath
            Glide.with(this)
                .load(args.tvShow.posterPath)
                .into(this)
        }
        initObservers()
        setupRecyclerView()
    }

    private fun bindDetailsView(details: TVShowDetails) {
        binding.pbRating.progress = details.voteAverage
        binding.tvOverviewText.text = details.overView
        binding.tvShowName.text = details.name
        binding.tvRatingPercent.setCharacterLists(TickerUtils.provideAlphabeticalList())
        binding.tvRatingPercent.text = details.voteAverage.toString()
        binding.tvTagline.text = details.tagLine
        Glide.with(requireContext()).load(details.backDropPath).transition(
            DrawableTransitionOptions.withCrossFade()
        ).into(binding.ivCover)
    }


    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.showDetails.collect {
                if (it.isLoading) {
                    Timber.d("THIS IS LOADING!!!")
                }
                if (!it.error.isNullOrEmpty()) {
                    Timber.d("ჩააერორა")
                }
                if (it.tvShowDetails != null) {
                    val details = it.tvShowDetails
                    bindDetailsView(details)
                }
            }
        }
    }


    private fun setupRecyclerView() {
        tvAdapter = SimilarShowsAdapter()



        binding.rvSimilar.apply {
            adapter = tvAdapter

            tvAdapter.apply {
                setOnItemClickListener {
                    findNavController().navigate(
                        ShowDetailsFragmentDirections.showDetailsFragmentToShowDetailsFragment(
                            it
                        )
                    )
                }
                withLoadStateHeaderAndFooter(
                    header = PopularTVsLoadStateAdapter { tvAdapter.retry() },
                    footer = PopularTVsLoadStateAdapter { tvAdapter.retry() })
            }
            val divider = DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.HORIZONTAL
            )
            divider.setDrawable(resources.getDrawable(R.drawable.divider_item_4w))
            addItemDecoration(
                divider, -1
            )

            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        }
        lifecycleScope.launch {
            viewModel.similarShows.collectLatest {
                tvAdapter.submitData(it)
            }
        }
    }
}