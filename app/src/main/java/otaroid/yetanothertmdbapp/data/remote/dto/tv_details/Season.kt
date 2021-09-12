package otaroid.yetanothertmdbapp.data.remote.dto.tv_details


import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("air_date")
    val airDate: String, // 2010-12-05
    @SerializedName("episode_count")
    val episodeCount: Int, // 64
    @SerializedName("id")
    val id: Int, // 3627
    @SerializedName("name")
    val name: String, // Specials
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String, // /kMTcwNRfFKCZ0O2OaBZS0nZ2AIe.jpg
    @SerializedName("season_number")
    val seasonNumber: Int // 0
)