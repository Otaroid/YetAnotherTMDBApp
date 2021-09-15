package otaroid.yetanothertmdbapp.data.remote.paging

sealed class TMdbRequestType(query: String = "", tvid: Int = 0) {
   class PopularTV : TMdbRequestType()
   data class SearchTV(val query: String) : TMdbRequestType(query = query)
   data class SimilarTV(val tvid: Int) : TMdbRequestType(tvid = tvid)

}