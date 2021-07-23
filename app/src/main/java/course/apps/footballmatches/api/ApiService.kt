package course.apps.footballmatches.api

import course.apps.footballmatches.pojo.MatchesInfoRAWData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {


    companion object {
        private const val API_KEY = "56b7c730819e5918546d6263afe7ad27"
        const val HEADER_API_KEY = "x-apisports-key"
        const val GB_PREMIER_LEAGUE = 39
        const val CURRENT_SEASON = 2021

        const val FIXTURE_KEY = "fixture"
        const val LEAGUE_KEY = "league"
        const val GOALS_KEY = "goals"
        const val TEAMS_KEY = "teams"
    }

    @Headers("$HEADER_API_KEY: $API_KEY")

    @GET("fixtures")
    fun getMatchesList(
        @Query("league") league: Int = GB_PREMIER_LEAGUE,
        @Query("season") season: Int = CURRENT_SEASON
    ) : Single<MatchesInfoRAWData>

}