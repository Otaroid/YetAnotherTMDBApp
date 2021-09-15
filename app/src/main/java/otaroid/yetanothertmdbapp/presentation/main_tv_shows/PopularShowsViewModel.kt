package otaroid.yetanothertmdbapp.presentation.main_tv_shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import otaroid.yetanothertmdbapp.domain.model.TVShow
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import otaroid.yetanothertmdbapp.domain.use_case.get_popular_tv_show.GetPopularShowsUseCase
import otaroid.yetanothertmdbapp.domain.use_case.search_tv_use_case.SearchTVShowsUseCase
import javax.inject.Inject

@HiltViewModel
class PopularShowsViewModel @Inject constructor(
    private val repository: TVShowsRepository,
) : ViewModel() {


    private val _popularShows: Flow<PagingData<TVShow>> = GetPopularShowsUseCase(
        repository
    ).invoke().cachedIn(viewModelScope)

    val popularShows: Flow<PagingData<TVShow>> get() = _popularShows


    private var _tvSearchResult: Flow<PagingData<TVShow>> = emptyFlow()
    val tvSearchResults: Flow<PagingData<TVShow>> get() = _tvSearchResult


    fun searchTVShows(query: String) {
        _tvSearchResult = SearchTVShowsUseCase(repository, query).invoke().cachedIn(viewModelScope)
    }


}
