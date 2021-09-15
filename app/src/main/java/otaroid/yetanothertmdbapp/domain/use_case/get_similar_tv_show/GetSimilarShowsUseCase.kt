package otaroid.yetanothertmdbapp.domain.use_case.get_popular_tv_show

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

class GetSimilarShowsUseCase constructor(
    private val repository: TVShowsRepository,
    private val tvId :Int
) {
    operator fun invoke(): Flow<PagingData<TVShow>> = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = {
            TvShowPagingSource(repository, TMdbRequestType.SimilarTV(tvId))
        }
    ).flow.map { pagingData ->
        pagingData.map { tv ->
            tv.toTVShow()
        }
    }
}