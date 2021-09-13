package otaroid.yetanothertmdbapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import otaroid.yetanothertmdbapp.common.Konstants
import otaroid.yetanothertmdbapp.data.remote.TMDBApi
import otaroid.yetanothertmdbapp.data.remote.dto.popular.TVShowDTO
import otaroid.yetanothertmdbapp.data.remote.paging.TvShowPagingSource
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import javax.inject.Inject

class TVShowsRepositoryImpl @Inject constructor(private val api: TMDBApi) : TVShowsRepository {
    override suspend fun getPopularTVShows(pageIndex: Int): TVShowDTO {
        return api.getPopularTV(pageIndex)
    }
}

//    override fun getPopularTVShows() =
//        Pager(
//            config = PagingConfig(pageSize = 10, maxSize = 100, enablePlaceholders = false),
//            pagingSourceFactory = {
//                TvShowPagingSource(api)
//            }
//        ).flow
