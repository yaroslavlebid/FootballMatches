package course.apps.footballmatches.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import course.apps.footballmatches.R
import course.apps.footballmatches.pojo.Match

class MatchInfoAdapter(private val context: Context) : RecyclerView.Adapter<MatchInfoAdapter.MatchInfoViewHolder>() {

    var matchesList: List<Match> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onMatchClickListener: OnMatchClickListener? = null

    interface OnMatchClickListener{
        fun onMatchClick(match: Match)
    }

    inner class MatchInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        //        var timestamp: Int? = null,
//        var statusOfMatch: String? = null,
//        var timeElapsed: Int? = null,
//        var homeGoals: Int? = null,
//        var awayGoals: Int? = null,
//        var leagueName: String? = null,
//        var leagueCountry: String? = null,
//        var leagueLogoImg: String? = null,
//        var countryFlagImg: String? = null,
//
//        var homeTeamName: String? = null,
//        var homeTeamLogo: String? = null,
//        var isHomeTeamWinner: Boolean? = null,
//
//        var awayTeamName: String? = null,
//        var awayTeamLogo: String? = null,
//        var isAwayTeamWinner: Boolean? = null,
//        var season: Int? = null,
//        var round: String? = null
        val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
        val textViewStatusOfMatch: TextView = itemView.findViewById(R.id.textViewStatusOfMatch)
        val textViewHomeGoals: TextView = itemView.findViewById(R.id.textViewHomeGoals)
        val textViewAwayGoals: TextView = itemView.findViewById(R.id.textViewAwayGoals)
        val textViewLeagueName: TextView = itemView.findViewById(R.id.textViewLeagueName)
        val textViewSeason: TextView = itemView.findViewById(R.id.textViewSeason)
        val textViewRound: TextView = itemView.findViewById(R.id.textViewRound)


        val imageViewLeagueLogo: ImageView = itemView.findViewById(R.id.imageViewLeagueLogo)
        val imageViewCountryFlag: ImageView = itemView.findViewById(R.id.imageViewCountryFlag)
        val imageViewHomeTeamLogo: ImageView = itemView.findViewById(R.id.imageViewHomeTeamLogo)
        val imageViewAwayTeamLogo: ImageView = itemView.findViewById(R.id.imageViewAwayTeamLogo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchInfoViewHolder, position: Int) {
        val match = matchesList[position]

        with(holder)
        {
            with(match)
            {
                textViewDate.text = getFormattedTime()
                textViewStatusOfMatch.text = statusOfMatch
                val home: String; val away: String
                if (homeGoals == null || awayGoals == null)
                {
                    home = "0"
                    away = "0"
                }
                else {
                    home = homeGoals.toString()
                    away = awayGoals.toString()
                }
                textViewHomeGoals.text = home
                textViewAwayGoals.text = away
                textViewLeagueName.text = leagueName
                textViewSeason.text = season.toString()
                textViewRound.text = round

                //Picasso.get().load(countryFlagImg).into(imageViewCountryFlag)
                Picasso.get().load(leagueLogoImg).into(imageViewLeagueLogo)
                Picasso.get().load(homeTeamLogo).into(imageViewHomeTeamLogo)
                Picasso.get().load(awayTeamLogo).into(imageViewAwayTeamLogo)
            }
        }
    }

    override fun getItemCount(): Int = matchesList.size
}