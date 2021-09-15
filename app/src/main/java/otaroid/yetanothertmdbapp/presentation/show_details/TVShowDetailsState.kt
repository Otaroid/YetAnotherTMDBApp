package otaroid.yetanothertmdbapp.presentation.show_details

import otaroid.yetanothertmdbapp.domain.model.TVShowDetails

data class TVShowDetailsState(
    val isLoading: Boolean = false,
    val tvShowDetails: TVShowDetails? = null,
    val error: String = ""
)