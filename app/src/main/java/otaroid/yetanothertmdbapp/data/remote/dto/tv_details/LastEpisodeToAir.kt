package otaroid.yetanothertmdbapp.data.remote.dto.tv_details


import com.google.gson.annotations.SerializedName

data class LastEpisodeToAir(
    @SerializedName("air_date")
    val airDate: String, // 2019-05-19
    @SerializedName("episode_number")
    val episodeNumber: Int, // 6
    @SerializedName("id")
    val id: Int, // 1551830
    @SerializedName("name")
    val name: String, // The Iron Throne
    @SerializedName("overview")
    val overview: String, // In the aftermath of the devastating attack on King's Landing, Daenerys must face the survivors.
    @SerializedName("production_code")
    val productionCode: String, // 806
    @SerializedName("season_number")
    val seasonNumber: Int, // 8
    @SerializedName("still_path")
    val stillPath: String, // /3x8tJon5jXFa1ziAM93hPKNyW7i.jpg
    @SerializedName("vote_average")
    val voteAverage: Double, // 4.8
    @SerializedName("vote_count")
    val voteCount: Int // 106
)