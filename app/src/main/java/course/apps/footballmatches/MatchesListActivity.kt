package course.apps.footballmatches

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import course.apps.footballmatches.adapters.MatchInfoAdapter
import course.apps.footballmatches.pojo.Match
import io.reactivex.disposables.CompositeDisposable

class MatchesListActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches_list)
        recyclerView = findViewById(R.id.recyclerViewMatchesList)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

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
        viewModel.loadData()

        viewModel.matchesList.observe(this, Observer {
            adapter.matchesList = it
        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId)
    {
        R.id.action_calendar -> {
            // open calendar
            Log.d("TEST_MENU", "User click on calendar menu item")
            true
        }
        R.id.action_filter -> {
            // open filter
            Log.d("TEST_MENU", "User click on filter menu item")
            true
        }
        R.id.action_search -> {
            // open search
            Log.d("TEST_MENU", "User click on search menu item")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}