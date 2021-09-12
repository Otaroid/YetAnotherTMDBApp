package otaroid.yetanothertmdbapp.presentation.popular_tv_shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import otaroid.yetanothertmdbapp.databinding.FragmentPopularShowsBinding
import otaroid.yetanothertmdbapp.presentation.popular_tv_shows.adapter.GridItemDecoration
import otaroid.yetanothertmdbapp.presentation.popular_tv_shows.adapter.PopularShowsAdapter


@AndroidEntryPoint
class PopularShowsFragment : Fragment() {
    private lateinit var tvAdapter : PopularShowsAdapter
    private val viewModel: PopularShowsViewModel by viewModels()
    private var _binding: FragmentPopularShowsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        initObservers()
    }


    private fun setupRecyclerView() {
        tvAdapter = PopularShowsAdapter()

        binding.rvPopularShows.apply {
            val spanCount = 2
            val spacing = 16


            adapter = tvAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, true)

            addItemDecoration(
                GridItemDecoration(spanCount, spacing, true)
            )

        }



    }

    private fun initObservers() {
        viewModel.popularShows.observe(viewLifecycleOwner, { result->
            result.tvShows?.let {tvShows->
                binding.rvPopularShows.visibility = View.VISIBLE
                binding.pb.visibility = View.GONE
                tvAdapter.differ.submitList(tvShows)
            }
            if (result.isLoading) {
                binding.rvPopularShows.visibility = View.GONE
                binding.pb.visibility = View.VISIBLE
            }
            if (result.error.isNullOrBlank()) {

            }

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PopularShowsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}