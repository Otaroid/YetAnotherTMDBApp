package otaroid.yetanothertmdbapp.presentation.main_tv_shows

import androidx.paging.PagingData
import otaroid.yetanothertmdbapp.domain.model.TVShow

data class TVShowsState(
    val isLoading: Boolean = false,
    val tvShows: PagingData<TVShow> = PagingData.empty(),
    val error: String = ""
)