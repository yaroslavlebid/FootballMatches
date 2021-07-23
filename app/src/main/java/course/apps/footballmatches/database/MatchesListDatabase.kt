package course.apps.footballmatches.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import course.apps.footballmatches.pojo.Match


@Database(entities = [Match::class], version = 1, exportSchema = false)
abstract class MatchesListDatabase() : RoomDatabase() {
    companion object {

        private var database: MatchesListDatabase? = null
        private val LOCK: Any = Any()
        private const val DB_NAME = "matches_list.db"

        fun getInstance(context: Context) : MatchesListDatabase
        {
            synchronized(LOCK)
            {
                database?.let { return it }
                val instance = Room.databaseBuilder(context, MatchesListDatabase::class.java, DB_NAME)
                        .build()
                database = instance
                return instance
            }
        }
    }

    abstract fun matchesListDao() : MatchesListDao
}