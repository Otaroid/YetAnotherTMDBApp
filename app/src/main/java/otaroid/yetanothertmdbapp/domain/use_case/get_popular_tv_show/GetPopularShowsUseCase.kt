package otaroid.yetanothertmdbapp.domain.use_case.get_popular_tv_show

import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import otaroid.yetanothertmdbapp.common.Resource
import otaroid.yetanothertmdbapp.data.remote.dto.popular.toTVShow
import otaroid.yetanothertmdbapp.domain.model.TVShow
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import java.io.IOException
import javax.inject.Inject

class GetPopularShowsUseCase @Inject constructor(private val repository: TVShowsRepository) {
    operator fun invoke(page: Int): Flow<Resource<List<TVShow>>> = flow {
        try {
            emit(Resource.Loading<List<TVShow>>())
            val tvShows = repository.getPopularTVShows(page).results.map { it.toTVShow() }
            emit(Resource.Success<List<TVShow>>(tvShows))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<TVShow>>(
                    e.localizedMessage ?: "Something went wrong with Popular TV Shows"
                )
            )
        } catch (e: IOException) {
            Resource.Error<List<TVShow>>(
                e.localizedMessage ?: "Can't fetch Popular TV Shows. Couldn't reach the server. Check your connection"
            )
        }
    }
}