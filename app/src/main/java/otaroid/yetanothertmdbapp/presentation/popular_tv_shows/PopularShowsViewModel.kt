package otaroid.yetanothertmdbapp.presentation.popular_tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.onEach
import otaroid.yetanothertmdbapp.common.Resource
import otaroid.yetanothertmdbapp.data.remote.dto.popular.toTVShow
import otaroid.yetanothertmdbapp.data.remote.paging.TvShowPagingSource
import otaroid.yetanothertmdbapp.domain.model.TVShow
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import otaroid.yetanothertmdbapp.domain.use_case.get_popular_tv_show.GetPopularShowsUseCase
import javax.inject.Inject

@HiltViewModel
class PopularShowsViewModel @Inject constructor(
    private val repository: TVShowsRepository,
) : ViewModel() {
    private val _popularShows = MutableStateFlow(PopularTVShows())
    val popularShows: Flow<PopularTVShows> get() = _popularShows


    val tvShows : Flow<PagingData<TVShow>> = getTVShowsStream().cachedIn(viewModelScope)


//    init {
//        getPopularShows()
//    }

    private fun getPopularShows() {

//        popularShowsUseCase()

//        popularShowsUseCase().map { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _popularShows.value = PopularTVShows(tvShows = result.data
//                        ?: PagingData.empty<TVShow>())
//                }
//                is Resource.Error -> {
//                    _popularShows.value =
//                        PopularTVShows(error = result.message ?: "Unexpected Error")
//                }
//                is Resource.Loading -> {
//                    _popularShows.value = PopularTVShows(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
    }

    private fun getTVShowsStream():Flow<PagingData<TVShow>>{
        return Pager(
            config = PagingConfig(pageSize = 10, maxSize = 100, enablePlaceholders = false),
            pagingSourceFactory = {
                TvShowPagingSource(repository)
            }
        ).flow.map { pagingData ->
            pagingData.map { tv ->
                tv.toTVShow()
            }
        }
    }
}