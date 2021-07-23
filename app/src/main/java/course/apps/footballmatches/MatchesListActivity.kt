package course.apps.footballmatches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import course.apps.footballmatches.adapters.MatchInfoAdapter
import course.apps.footballmatches.api.ApiFactory
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