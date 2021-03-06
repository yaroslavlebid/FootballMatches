package course.apps.footballmatches.database

import androidx.lifecycle.LiveData
import androidx.room.*
import course.apps.footballmatches.pojo.Match

@Dao
interface MatchesListDao {

    @Query("SELECT * FROM matches_list ORDER BY timestamp")
    fun getMatchesList() : LiveData<List<Match>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchesList(matches: List<Match>)

    @Query("DELETE from matches_list")
    fun clearMatchesList()

    @Query("SELECT * FROM matches_list WHERE id == :fixtureId LIMIT 1")
    fun getMatchById(fixtureId: Int) : LiveData<Match>

}