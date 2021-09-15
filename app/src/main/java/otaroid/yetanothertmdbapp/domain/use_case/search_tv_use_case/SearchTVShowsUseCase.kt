package otaroid.yetanothertmdbapp.domain.use_case.search_tv_use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import otaroid.yetanothertmdbapp.data.remote.dto.popular.toTVShow
import otaroid.yetanothertmdbapp.data.remote.paging.TMdbRequestType
import otaroid.yetanothertmdbapp.data.remote.paging.TvShowPagingSource
import otaroid.yetanothertmdbapp.domain.model.TVShow
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import javax.inject.Inject

class SearchTVShowsUseCase @Inject constructor(
    private val repository: TVShowsRepository,
    private val searchQuery: String,
) {
    operator fun invoke(): Flow<PagingData<TVShow>> = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = {
            TvShowPagingSource(repository, TMdbRequestType.SearchTV(searchQuery))
        }
    ).flow.map { pagingData ->
        pagingData.map { tv ->
            tv?.let {
                tv.toTVShow()
            }
        }
    }
}