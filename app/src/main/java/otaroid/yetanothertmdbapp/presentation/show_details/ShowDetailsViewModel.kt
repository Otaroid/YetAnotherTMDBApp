package otaroid.yetanothertmdbapp.presentation.show_details


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import otaroid.yetanothertmdbapp.common.Resource
import otaroid.yetanothertmdbapp.domain.model.TVShow
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import otaroid.yetanothertmdbapp.domain.use_case.get_popular_tv_show.GetSimilarShowsUseCase
import otaroid.yetanothertmdbapp.domain.use_case.tv_details_use_case.GetShowDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val getShowDetailsUseCase: GetShowDetailsUseCase,
    private val repository: TVShowsRepository,
    private val state: SavedStateHandle
) : ViewModel() {
    private var _showDetails = MutableStateFlow(TVShowDetailsState())
    val showDetails: StateFlow<TVShowDetailsState> get() = _showDetails

    private var _similarShows = emptyFlow<PagingData<TVShow>>()

    val similarShows: Flow<PagingData<TVShow>> get() = _similarShows


    init {
        val tvShow = state.get<TVShow>("tvShow")
        tvShow?.let { show ->
            getTvShowDetails(show.id)
            _similarShows =
                GetSimilarShowsUseCase(repository, show.id).invoke().cachedIn(viewModelScope)
        }

    }


    private fun getTvShowDetails(showId: Int) {
        getShowDetailsUseCase(showId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _showDetails.value = TVShowDetailsState(tvShowDetails = result.data)
                }
                is Resource.Error -> {
                    _showDetails.value =
                        TVShowDetailsState(error = result.message ?: "Unexpected Error")
                }
                is Resource.Loading -> {
                    _showDetails.value = TVShowDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}