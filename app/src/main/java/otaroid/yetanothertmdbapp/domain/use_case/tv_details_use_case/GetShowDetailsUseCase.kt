package otaroid.yetanothertmdbapp.domain.use_case.tv_details_use_case

import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import otaroid.yetanothertmdbapp.common.Resource
import otaroid.yetanothertmdbapp.data.remote.dto.popular.toTVShow
import otaroid.yetanothertmdbapp.data.remote.dto.tv_details.toTVShowDetails
import otaroid.yetanothertmdbapp.domain.model.TVShow
import otaroid.yetanothertmdbapp.domain.model.TVShowDetails
import otaroid.yetanothertmdbapp.domain.repository.TVShowsRepository
import java.io.IOException
import javax.inject.Inject

class GetShowDetailsUseCase @Inject constructor(private val repository: TVShowsRepository) {
    operator fun invoke(showId: Int): Flow<Resource<TVShowDetails>> = flow {
        try {
            emit(Resource.Loading<TVShowDetails>())
            val tvShows = repository.getTVShowDetails(showId).toTVShowDetails()
            emit(Resource.Success<TVShowDetails>(tvShows))
        } catch (e: HttpException) {
            emit(
                Resource.Error<TVShowDetails>(
                    e.localizedMessage ?: "Something went wrong with Popular TV Shows"
                )
            )
        } catch (e: IOException) {
            Resource.Error<TVShowDetails>(
                e.localizedMessage ?: "Can't fetch Popular TV Shows. Couldn't reach the server. Check your connection"
            )
        }
    }
}