package otaroid.yetanothertmdbapp.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import otaroid.yetanothertmdbapp.data.remote.dto.popular.TV
import otaroid.yetanothertmdbapp.data.remote.dto.popular.TVShowDTO

interface TVShowsRepository {
    suspend fun getPopularTVShows(pageIndex:Int): TVShowDTO
}