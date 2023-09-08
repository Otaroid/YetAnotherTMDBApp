package otaroid.yetanothertmdbapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShow(
    val id: Int,
    val name: String,
    val genreIds: List<Int>,
    val overview: String,
    val rating: Int,
    val posterPath: String,
    val backDropPath: String,
    val firstAirDate: String
) : Parcelable
