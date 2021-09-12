package otaroid.yetanothertmdbapp.data.remote.dto.popular


import com.google.gson.annotations.SerializedName

data class TVShowDTO(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<TV>,
    @SerializedName("total_pages")
    val totalPages: Int, // 1000
    @SerializedName("total_results")
    val totalResults: Int // 20000
)