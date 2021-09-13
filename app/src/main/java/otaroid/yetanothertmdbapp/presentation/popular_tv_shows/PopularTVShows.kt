package otaroid.yetanothertmdbapp.presentation.popular_tv_shows

import androidx.paging.PagingData
import otaroid.yetanothertmdbapp.domain.model.TVShow

data class PopularTVShows(
    val isLoading: Boolean = false,
    val tvShows: PagingData<TVShow> = PagingData.empty(),
    val error: String = ""
)