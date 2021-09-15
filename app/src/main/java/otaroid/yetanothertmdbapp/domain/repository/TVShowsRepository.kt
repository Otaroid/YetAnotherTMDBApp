package otaroid.yetanothertmdbapp.domain.repository

import otaroid.yetanothertmdbapp.data.remote.dto.credits.TVShowCreditsDTO
import otaroid.yetanothertmdbapp.data.remote.dto.popular.TVShowDTO
import otaroid.yetanothertmdbapp.data.remote.dto.tv_details.ShowDetailsDTO

interface TVShowsRepository {
    suspend fun getPopularTVShows(pageIndex: Int): TVShowDTO

    suspend fun getTVShowDetails(showId: Int): ShowDetailsDTO
    suspend fun getTVShowSearchResults(pageIndex: Int, query: String): TVShowDTO
    suspend fun getSimilarTVShows(showId: Int, pageIndex:Int): TVShowDTO
    suspend fun getTVShowCredits(showId: Int): TVShowCreditsDTO
}