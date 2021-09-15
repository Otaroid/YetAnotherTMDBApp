package otaroid.yetanothertmdbapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TVShowDetails(
    val id: Int,
    val name: String,
    val backDropPath: String,
    val posterPath: String,
    val airDate: String,
    val tagLine: String,
    val overView: String,
    val voteAverage: Int,
) : Parcelable
