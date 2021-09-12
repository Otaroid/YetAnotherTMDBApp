package otaroid.yetanothertmdbapp.presentation.popular_tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import otaroid.yetanothertmdbapp.common.Resource
import otaroid.yetanothertmdbapp.domain.use_case.get_popular_tv_show.GetPopularShowsUseCase
import javax.inject.Inject

@HiltViewModel
class PopularShowsViewModel @Inject constructor(
    private val popularShowsUseCase: GetPopularShowsUseCase
) : ViewModel() {
    private val _popularShows = MutableLiveData(PopularTVShows())
    val popularShows: LiveData<PopularTVShows> get() = _popularShows

    init {
        getPopularShows(1)
    }

    private fun getPopularShows(pageId: Int) {
        popularShowsUseCase(pageId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _popularShows.value = PopularTVShows(tvShows = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _popularShows.value =
                        PopularTVShows(error = result.message ?: "Unexpected Error")
                }
                is Resource.Loading -> {
                    _popularShows.value = PopularTVShows(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}