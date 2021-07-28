package course.apps.footballmatches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import course.apps.footballmatches.adapters.MatchInfoAdapter
import course.apps.footballmatches.api.ApiFactory
import course.apps.footballmatches.pojo.Match
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MatchesListActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches_list)
        recyclerView = findViewById(R.id.recyclerViewMatchesList)

        val adapter = MatchInfoAdapter(this)
        adapter.onMatchClickListener = object : MatchInfoAdapter.OnMatchClickListener {
            override fun onMatchClick(match: Match) {
                Log.d("TEST_ON_MATCH_CLICK", "Match(fixture): $match")
                val intent = MatchDetailActivity.newIntent(match.id, this@MatchesListActivity)
                if (intent == null)
                    Toast.makeText(this@MatchesListActivity, "Error", Toast.LENGTH_SHORT)
                else
                    startActivity(intent)
            }

        }
        recyclerView.adapter = adapter

        val viewModel by viewModels<MatchesListViewModel>()
        //viewModel.loadData()

        viewModel.matchesList.observe(this, Observer {
            adapter.matchesList = it
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}