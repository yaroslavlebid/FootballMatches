package course.apps.footballmatches.pojo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


@Entity(tableName = "matches_list")
data class Match(
        @PrimaryKey
        @NonNull
        var id: Int? = 0,
        var timestamp: Int? = null,
        var statusOfMatch: String? = null,
        var timeElapsed: Int? = null,
        var homeGoals: Int? = null,
        var awayGoals: Int? = null,
        var leagueName: String? = null,
        var leagueCountry: String? = null,
        var leagueLogoImg: String? = null,
        var countryFlagImg: String? = null,

        var homeTeamName: String? = null,
        var homeTeamLogo: String? = null,
        var isHomeTeamWinner: Boolean? = null,

        var awayTeamName: String? = null,
        var awayTeamLogo: String? = null,
        var isAwayTeamWinner: Boolean? = null,
        var season: Int? = null
)