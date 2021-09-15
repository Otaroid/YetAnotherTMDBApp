package otaroid.yetanothertmdbapp.data.remote.dto.credits


import com.google.gson.annotations.SerializedName

data class TVShowCreditsDTO(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int // 1399
)