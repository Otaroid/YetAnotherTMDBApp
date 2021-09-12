package otaroid.yetanothertmdbapp.data.remote.dto.tv_details


import com.google.gson.annotations.SerializedName

data class Network(
    @SerializedName("id")
    val id: Int, // 49
    @SerializedName("logo_path")
    val logoPath: String, // /tuomPhY2UtuPTqqFnKMVHvSb724.png
    @SerializedName("name")
    val name: String, // HBO
    @SerializedName("origin_country")
    val originCountry: String // US
)