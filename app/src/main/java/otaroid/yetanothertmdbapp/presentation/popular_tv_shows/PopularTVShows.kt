package otaroid.yetanothertmdbapp.presentation.popular_tv_shows

import otaroid.yetanothertmdbapp.domain.model.TVShow

data class PopularTVShows(
    val isLoading: Boolean = false,
    val tvShows: List<TVShow> = emptyList(),
    val error: String = ""
)