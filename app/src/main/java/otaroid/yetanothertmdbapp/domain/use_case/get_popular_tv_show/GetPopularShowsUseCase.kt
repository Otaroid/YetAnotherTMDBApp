package otaroid.yetanothertmdbapp.domain.use_case.get_popular_tv_show

import androidx.paging.*
import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import otaroid.yetanothertmdbapp.common.Resource
import otaroid.yetanothertmdbapp.data.remote.dto.popular.toTVShow
import otaroid.yetanothertmdbapp.data.remote.paging.TvShowPagingSource
import otaroid.yetanothertmdbapp.domain.model.TVShow
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import java.io.IOException
import javax.inject.Inject

class GetPopularShowsUseCase @Inject constructor(private val repository: TVShowsRepository) {
    operator fun invoke(): Flow<PagingData<TVShow>> = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = {
            TvShowPagingSource(repository)
        }
    ).flow.map { pagingData ->
        pagingData.map { tv ->
            tv.toTVShow()
        }
    }
}