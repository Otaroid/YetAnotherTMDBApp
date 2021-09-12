package otaroid.yetanothertmdbapp.data.remote.dto.tv_details


import com.google.gson.annotations.SerializedName

data class ShowDetailsDTO(
    @SerializedName("backdrop_path")
    val backdropPath: String, // /suopoADq0k8YZr4dQXcU6pToj6s.jpg
    @SerializedName("created_by")
    val createdBy: List<CreatedBy>,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerializedName("first_air_date")
    val firstAirDate: String, // 2011-04-17
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("homepage")
    val homepage: String, // http://www.hbo.com/game-of-thrones
    @SerializedName("id")
    val id: Int, // 1399
    @SerializedName("in_production")
    val inProduction: Boolean, // false
    @SerializedName("languages")
    val languages: List<String>,
    @SerializedName("last_air_date")
    val lastAirDate: String, // 2019-05-19
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir,
    @SerializedName("name")
    val name: String, // Game of Thrones
    @SerializedName("networks")
    val networks: List<Network>,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Any, // null
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int, // 73
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int, // 8
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_name")
    val originalName: String, // Game of Thrones
    @SerializedName("overview")
    val overview: String, // Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.
    @SerializedName("popularity")
    val popularity: Double, // 369.594
    @SerializedName("poster_path")
    val posterPath: String, // /u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerializedName("seasons")
    val seasons: List<Season>,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: String, // Ended
    @SerializedName("tagline")
    val tagline: String, // Winter Is Coming
    @SerializedName("type")
    val type: String, // Scripted
    @SerializedName("vote_average")
    val voteAverage: Double, // 8.3
    @SerializedName("vote_count")
    val voteCount: Int // 11504
)