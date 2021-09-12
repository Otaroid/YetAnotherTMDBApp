package otaroid.yetanothertmdbapp.data.remote.dto.tv_details


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("id")
    val id: Int, // 76043
    @SerializedName("logo_path")
    val logoPath: String, // /9RO2vbQ67otPrBLXCaC8UMp3Qat.png
    @SerializedName("name")
    val name: String, // Revolution Sun Studios
    @SerializedName("origin_country")
    val originCountry: String // US
)