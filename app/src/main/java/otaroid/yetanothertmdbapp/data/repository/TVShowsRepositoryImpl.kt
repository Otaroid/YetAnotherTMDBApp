package otaroid.yetanothertmdbapp.data.repository

import otaroid.yetanothertmdbapp.data.remote.TMDBApi
import otaroid.yetanothertmdbapp.data.remote.dto.credits.TVShowCreditsDTO
import otaroid.yetanothertmdbapp.data.remote.dto.popular.TVShowDTO
import otaroid.yetanothertmdbapp.data.remote.dto.tv_details.ShowDetailsDTO
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import timber.log.Timber
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(private val api: TMDBApi) : TVShowsRepository {
    override suspend fun getPopularTVShows(pageIndex: Int): TVShowDTO {
        return api.getPopularTV(pageIndex)
    }

    override suspend fun getTVShowDetails(showId: Int): ShowDetailsDTO {

        return api.getShowDetailsById(showId)
    }

    override suspend fun getTVShowSearchResults(pageIndex: Int, query: String): TVShowDTO {
        Timber.d("Search Query $query")
        return api.getTVShowSearchResults(query, pageIndex)
    }

    override suspend fun getSimilarTVShows(showId: Int, pageIndex: Int): TVShowDTO {
        Timber.d("Similar TV $showId")
        return api.getSimilarTVShows(showId, pageIndex)
    }

    override suspend fun getTVShowCredits(showId: Int): TVShowCreditsDTO {

        return api.getTVShowCredits(showId)
    }
}

