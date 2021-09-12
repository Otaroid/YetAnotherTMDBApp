package otaroid.yetanothertmdbapp.data.repository

import otaroid.yetanothertmdbapp.data.remote.TMDBApi
import otaroid.yetanothertmdbapp.data.remote.dto.popular.TVShowDTO
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(private val api: TMDBApi) : TVShowsRepository {
    override suspend fun getPopularTVShows(page: Int): TVShowDTO {
        return api.getPopularTV(page)
    }

}