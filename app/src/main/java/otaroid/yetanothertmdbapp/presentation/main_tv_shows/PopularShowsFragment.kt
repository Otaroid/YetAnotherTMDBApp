package otaroid.yetanothertmdbapp.presentation.main_tv_shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import otaroid.yetanothertmdbapp.databinding.FragmentPopularShowsBinding
import otaroid.yetanothertmdbapp.presentation.main_tv_shows.adapter.GridItemDecoration
import otaroid.yetanothertmdbapp.presentation.main_tv_shows.adapter.PopularTVsLoadStateAdapter
import otaroid.yetanothertmdbapp.presentation.main_tv_shows.adapter.TVShowsAdapter
import java.util.*
import kotlin.concurrent.schedule


@AndroidEntryPoint
class PopularShowsFragment : Fragment() {
    private lateinit var tvAdapter: TVShowsAdapter
    private val viewModel: PopularShowsViewModel by viewModels()
    private var _binding: FragmentPopularShowsBinding? = null
    private val binding get() = _binding!!

    val searchTimer = Timer("searchTimer", true)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        initObservers()
        searchTV()
    }

    private fun searchTV() {
        binding.svSearch.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    p0?.let {
                        if (!p0.isNullOrBlank()) {
                            viewModel.searchTVShows(p0)
                        } else {
                            lifecycleScope.launch {
                                viewModel.popularShows.collect {
                                    tvAdapter.submitData(it)
                                }
                            }
                        }
                    }
                    lifecycleScope.launch {
                        viewModel.tvSearchResults.collectLatest {

                            tvAdapter.submitData(it)
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    searchTimer.schedule(300) {
                        p0?.let {
                            if (!p0.isNullOrBlank()) {
                                viewModel.searchTVShows(p0)
                            } else {
                                lifecycleScope.launch {
                                    viewModel.popularShows.collect {

                                        tvAdapter.submitData(it)
                                    }
                                }
                            }
                        }
                        lifecycleScope.launch {
                            viewModel.tvSearchResults.collectLatest {

                                tvAdapter.submitData(it)
                            }
                        }
                    }
                    return true
                }

            })

            setOnCloseListener {
                lifecycleScope.launch {
                    viewModel.popularShows.collectLatest {

                        tvAdapter.submitData(it)
                    }
                }
                true
            }

        }


    }

    private fun setupRecyclerView() {
        tvAdapter = TVShowsAdapter()



        binding.rvPopularShows.apply {
            val spanCount = 2
            val spacing = 16
            adapter = tvAdapter

            tvAdapter.apply {
                setOnItemClickListener {
                    findNavController().navigate(
                        PopularShowsFragmentDirections.actionPopularShowsFragmentToShowDetailsFragment(
                            it
                        )
                    )
                }
                withLoadStateHeaderAndFooter(
                    header = PopularTVsLoadStateAdapter { tvAdapter.retry() },
                    footer = PopularTVsLoadStateAdapter { tvAdapter.retry() })
            }
            adapter = tvAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

            addItemDecoration(
                GridItemDecoration(spanCount, spacing, true)
            )

        }
        lifecycleScope.launch {
            viewModel.popularShows.collectLatest {

                tvAdapter.submitData(it)
            }
        }


    }

    private fun initObservers() {

//        lifecycleScope.launchWhenStarted {
//            viewModel.popularShows.collect { result ->
//                tvAdapter.submitData(viewLifecycleOwner.lifecycle, result.tvShows)
//            }
//        }

//        viewModel.popularShows.collect(viewLifecycleOwner, { result->
//            result.tvShows?.let {tvShows->
//                binding.rvPopularShows.visibility = View.VISIBLE
//                binding.pb.visibility = View.GONE
//                tvAdapter.differ.submitList(tvShows)
//            }
//            if (result.isLoading) {
//                binding.rvPopularShows.visibility = View.GONE
//                binding.pb.visibility = View.VISIBLE
//            }
//            if (result.error.isNullOrBlank()) {
//
//            }
//
//        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}