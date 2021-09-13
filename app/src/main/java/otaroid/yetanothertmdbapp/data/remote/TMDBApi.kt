package otaroid.yetanothertmdbapp.data.remote

import otaroid.yetanothertmdbapp.data.remote.dto.popular.TVShowDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("tv/popular")
    suspend fun getPopularTV(@Query("page") page:Int): TVShowDTO

    @GET("tv/{tv_id}")
    suspend fun getShowDetailsById(@Query("tv_id") page: Int): TVShowDTO

    @GET("tv/{tv_id}")
    suspend fun getTVShowSearchResults(
        @Query("page") page: Int,
        @Query("query") searchQuery: String
    ): TVShowDTO
}