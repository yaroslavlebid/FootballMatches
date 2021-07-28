package course.apps.footballmatches.pojo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import course.apps.footballmatches.utils.convertTimestampToDate


@Entity(tableName = "matches_list")
data class Match(
        @PrimaryKey
        @NonNull
        var id: Int? = 0,
        var timestamp: Long? = null,
        var statusOfMatch: String? = null,
        var timeElapsed: Int? = null,
        var homeGoals: Int? = null,
        var awayGoals: Int? = null,
        var leagueName: String? = null,
        var leagueCountry: String? = null,
        var leagueLogoImg: String? = null,
        var countryFlagImg: String? = null,

        var homeTeamName: String? = null,
        var homeTeamId: Int? = null,
        var homeTeamLogo: String? = null,
        var isHomeTeamWinner: Boolean? = null,

        var awayTeamName: String? = null,
        var awayTeamId: Int? = null,
        var awayTeamLogo: String? = null,
        var isAwayTeamWinner: Boolean? = null,
        var season: Int? = null,
        var round: String? = null
)

{
        fun getFormattedTime() : String
        {
                return convertTimestampToDate(timestamp)
        }
}