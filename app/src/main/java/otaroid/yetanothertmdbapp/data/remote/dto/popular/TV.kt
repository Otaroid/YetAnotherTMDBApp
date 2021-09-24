package otaroid.yetanothertmdbapp.data.remote.dto.popular


import com.google.gson.annotations.SerializedName
import otaroid.yetanothertmdbapp.common.Konstants.IMAGES_BASE_URL
import otaroid.yetanothertmdbapp.domain.model.TVShow

data class TV(
    @SerializedName("backdrop_path")
    val backdropPath: String, // /rQGBjWNveVeF8f2PGRtS85w9o9r.jpg
    @SerializedName("first_air_date")
    val firstAirDate: String? = null, // 2010-06-08
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 31917
    @SerializedName("name")
    val name: String, // Pretty Little Liars
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_name")
    val originalName: String, // Pretty Little Liars
    @SerializedName("overview")
    val overview: String, // Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name "A" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew.
    @SerializedName("popularity")
    val popularity: Double, // 47.432451
    @SerializedName("poster_path")
    val posterPath: String, // /vC324sdfcS313vh9QXwijLIHPJp.jpg
    @SerializedName("vote_average")
    val voteAverage: Double, // 5.04
    @SerializedName("vote_count")
    val voteCount: Int // 133
)

fun TV.toTVShow(): TVShow {

    return TVShow(
        id = id,
        name = name,
        genreIds = genreIds,
        overview = overview,
        rating = voteAverage.times(10).toInt(),
        posterPath = IMAGES_BASE_URL + posterPath,
        backDropPath = IMAGES_BASE_URL + backdropPath,
        firstAirDate = if (firstAirDate.isNullOrEmpty()) "" else firstAirDate,
    )
}



