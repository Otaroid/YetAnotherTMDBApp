package otaroid.yetanothertmdbapp.data.remote.dto.credits


import com.google.gson.annotations.SerializedName

data class Crew(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("credit_id")
    val creditId: String, // 5ceab0ab92514175e8bb5caf
    @SerializedName("department")
    val department: String, // Production
    @SerializedName("gender")
    val gender: Int, // 2
    @SerializedName("id")
    val id: Int, // 33316
    @SerializedName("job")
    val job: String, // Executive Producer
    @SerializedName("known_for_department")
    val knownForDepartment: String, // Directing
    @SerializedName("name")
    val name: String, // David Nutter
    @SerializedName("original_name")
    val originalName: String, // David Nutter
    @SerializedName("popularity")
    val popularity: Double, // 2.265
    @SerializedName("profile_path")
    val profilePath: Any // null
)