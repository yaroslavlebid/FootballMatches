package course.apps.footballmatches

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import course.apps.footballmatches.databinding.ActivityMatchDetailBinding

class MatchDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (!intent.hasExtra(EXTRA_MATCH_ID)) {
            finish()
            return
        }
        val matchId = intent.getIntExtra(EXTRA_MATCH_ID, -1)
        if(matchId == -1)
            return
        val viewModel by viewModels<MatchesListViewModel>()
        viewModel.getMatchById(matchId).observe(this, Observer {
            with(binding.itemMatch)
            {
                with(it)
                {
                    Log.d("TEST_SELECTED_MATCH", "Current match: $it")
                    textViewHomeName.text = homeTeamName
                    textViewAwayName.text = awayTeamName
                    textViewDate.text = getFormattedTime()
                    textViewStatusOfMatch.text = statusOfMatch
                    val home: String;
                    val away: String
                    if (homeGoals == null || awayGoals == null) {
                        home = "0"
                        away = "0"
                    } else {
                        home = homeGoals.toString()
                        away = awayGoals.toString()
                    }
                    textViewHomeGoals.text = home
                    textViewAwayGoals.text = away
                    textViewLeagueName.text = leagueName
                    textViewSeason.text = season.toString()
                    textViewRound.text = round

                    // TODO: countryFlagImg is SVG, need to fix it
                    //Picasso.get().load(countryFlagImg).into(imageViewCountryFlag)
                    Picasso.get().load(leagueLogoImg).into(imageViewLeagueLogo)
                    Picasso.get().load(homeTeamLogo).into(imageViewHomeTeamLogo)
                    Picasso.get().load(awayTeamLogo).into(imageViewAwayTeamLogo)
                }
            }
        })
    }

    companion object {
        private const val EXTRA_MATCH_ID = "matchId"

        fun newIntent(matchId: Int?, context: Context): Intent?
        {
            if(matchId == null)
            {
                return null
            }
            val intent = Intent(context, MatchDetailActivity::class.java)
            intent.putExtra(EXTRA_MATCH_ID, matchId)
            return intent
        }
    }
}