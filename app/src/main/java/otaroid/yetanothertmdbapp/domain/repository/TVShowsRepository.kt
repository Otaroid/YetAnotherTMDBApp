package otaroid.yetanothertmdbapp.domain.repository

import otaroid.yetanothertmdbapp.data.remote.dto.popular.TVShowDTO

interface TVShowsRepository {
    suspend fun getPopularTVShows(page:Int): TVShowDTO
}