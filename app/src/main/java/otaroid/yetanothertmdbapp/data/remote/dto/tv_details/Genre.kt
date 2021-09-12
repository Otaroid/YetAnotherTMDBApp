package otaroid.yetanothertmdbapp.data.remote.dto.tv_details


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Int, // 10765
    @SerializedName("name")
    val name: String // Sci-Fi & Fantasy
)