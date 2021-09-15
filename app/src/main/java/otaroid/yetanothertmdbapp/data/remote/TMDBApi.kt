package otaroid.yetanothertmdbapp.data.remote

import otaroid.yetanothertmdbapp.data.remote.dto.credits.TVShowCreditsDTO
import otaroid.yetanothertmdbapp.data.remote.dto.popular.TVShowDTO
import otaroid.yetanothertmdbapp.data.remote.dto.tv_details.ShowDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {
    @GET("tv/popular")
    suspend fun getPopularTV(@Query("page") page: Int): TVShowDTO

    @GET("tv/{tv_id}")
    suspend fun getShowDetailsById(@Path("tv_id") tvId: Int): ShowDetailsDTO

    @GET("search/tv")
    suspend fun getTVShowSearchResults(
        @Query("query") searchQuery: String,
        @Query("page") page: Int
    ): TVShowDTO

    @GET("tv/{tv_id}/credits")
    suspend fun getTVShowCredits(@Path("tv_id") tvId: Int): TVShowCreditsDTO

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTVShows(
        @Path("tv_id") tvId: Int,
        @Query("page") page: Int
    ): TVShowDTO
}