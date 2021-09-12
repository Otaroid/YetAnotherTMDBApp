package otaroid.yetanothertmdbapp.data.remote.dto.tv_details


import com.google.gson.annotations.SerializedName

data class CreatedBy(
    @SerializedName("credit_id")
    val creditId: String, // 5256c8c219c2956ff604858a
    @SerializedName("gender")
    val gender: Int, // 2
    @SerializedName("id")
    val id: Int, // 9813
    @SerializedName("name")
    val name: String, // David Benioff
    @SerializedName("profile_path")
    val profilePath: String // /xvNN5huL0X8yJ7h3IZfGG4O2zBD.jpg
)