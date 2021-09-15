package otaroid.yetanothertmdbapp.data.remote.dto.credits


import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("character")
    val character: String, // Daenerys Targaryen
    @SerializedName("credit_id")
    val creditId: String, // 5256c8af19c2956ff60479f6
    @SerializedName("gender")
    val gender: Int, // 1
    @SerializedName("id")
    val id: Int, // 1223786
    @SerializedName("known_for_department")
    val knownForDepartment: String, // Acting
    @SerializedName("name")
    val name: String, // Emilia Clarke
    @SerializedName("order")
    val order: Int, // 0
    @SerializedName("original_name")
    val originalName: String, // Emilia Clarke
    @SerializedName("popularity")
    val popularity: Double, // 14.322
    @SerializedName("profile_path")
    val profilePath: String // /r6i4C3kYrBRzUzZ8JKAHYQ0T0dD.jpg
)