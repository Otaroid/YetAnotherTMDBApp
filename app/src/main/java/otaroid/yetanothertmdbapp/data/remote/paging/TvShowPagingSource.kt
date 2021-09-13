package otaroid.yetanothertmdbapp.data.remote.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState

import otaroid.yetanothertmdbapp.common.Konstants.TMDB_STARTING_PAGE_INDEX
import otaroid.yetanothertmdbapp.data.remote.dto.popular.TV
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import retrofit2.HttpException
import java.io.IOException

class TvShowPagingSource constructor(
    private val repository: TVShowsRepository,
) : PagingSource<Int, TV>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TV> {
        val currentPage = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = repository.getPopularTVShows(currentPage).results
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == TMDB_STARTING_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (response.isEmpty()) null else currentPage + 1,
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TV>): Int? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.id
        }
    }
}