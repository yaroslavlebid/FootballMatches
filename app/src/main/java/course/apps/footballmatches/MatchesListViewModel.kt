package course.apps.footballmatches

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import course.apps.footballmatches.api.ApiFactory
import course.apps.footballmatches.api.ApiService
import course.apps.footballmatches.database.MatchesListDatabase
import course.apps.footballmatches.pojo.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MatchesListViewModel(application: Application) : AndroidViewModel(application) {
    private val db = MatchesListDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val matchesList = db.matchesListDao().getMatchesList()

    init {
        loadData()
    }

    fun loadData()
    {
        val disposable = ApiFactory.apiService.getMatchesList()
                .map { getMatchesListFromRAWData(it) }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.d("TEST_OF_DATA_LOADING", "Success: $it")
                    db.matchesListDao().insertMatchesList(it)
                },{
                    Log.d("TEST_OF_DATA_LOADING", "Failure: $it")
                })
        compositeDisposable.add(disposable)
    }

    private fun getMatchesListFromRAWData(rawData: MatchesInfoRAWData) : List<Match>
    {
        Log.d("PARSE_TEST", rawData.toString())
        val result = arrayListOf<Match>()
        val jsonMatches = rawData.matches ?: return result
        for (jsonObject in jsonMatches)
        {
            val match = Match()
            val fixtureJson = jsonObject.getAsJsonObject(ApiService.FIXTURE_KEY)
            val fixture = Gson().fromJson(fixtureJson, FixtureInfo::class.java)
            match.id = fixture.id
            match.timestamp = fixture.timestamp
            match.timeElapsed = fixture.status?.elapsed
            match.statusOfMatch = fixture.status?._short
            val leagueJson = jsonObject.getAsJsonObject(ApiService.LEAGUE_KEY)
            val league = Gson().fromJson(leagueJson, LeagueInfo::class.java)
            match.leagueName = league.name
            match.leagueCountry = league.country
            match.leagueLogoImg = league.logoImg
            match.countryFlagImg = league.flagImg
            match.season = league.season
            val goalsJson = jsonObject.getAsJsonObject(ApiService.GOALS_KEY)
            val goals = Gson().fromJson(goalsJson, GoalsInfo::class.java)
            match.homeGoals = goals.home
            match.awayGoals = goals.away
            val teamJson = jsonObject.getAsJsonObject(ApiService.TEAMS_KEY)
            val team = Gson().fromJson(teamJson, TeamsInfo::class.java)
            match.homeTeamName = team.home?.name
            match.homeTeamLogo = team.home?.logo
            match.isHomeTeamWinner = team.home?.winner

            match.awayTeamName = team.away?.name
            match.awayTeamLogo = team.away?.logo
            match.isAwayTeamWinner = team.away?.winner
            result.add(match)
        }
        return result
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}