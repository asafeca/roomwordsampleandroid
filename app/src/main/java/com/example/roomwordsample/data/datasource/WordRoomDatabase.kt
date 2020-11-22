package com.example.roomwordsample.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomwordsample.data.datasource.dao.LocalMigrations
import com.example.roomwordsample.data.datasource.dao.WordDao
import com.example.roomwordsample.data.model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Word::class), version = 3, exportSchema = false)
abstract class WordRoomDatabase:RoomDatabase() {
    abstract  fun wordDao():WordDao
    companion object{

        @Volatile
        private var INSTANCE:WordRoomDatabase?=null


        fun getDatabase(context: Context,scope: CoroutineScope):WordRoomDatabase{

            return INSTANCE?: synchronized(this){
                val instance=  Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"

                )
                    .addCallback(WordDatabaseCallback(scope))
                    .addMigrations(LocalMigrations.MIGRATION_1_2, LocalMigrations.MIGRATION_2_3)
                    .build()

                INSTANCE =instance

                instance
            }



        }



    }



    private class WordDatabaseCallback(private val scope:CoroutineScope):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                val database= it
                scope.launch {

                    populateDatabase(database.wordDao())

                }

            }
        }


        suspend fun populateDatabase(wordDao:WordDao){
            wordDao.deleteAll()
                var  word=Word(word = "Hello",slug = "vcs")
            wordDao.insert(word)
            word = Word("World",slug = "vcs")
            wordDao.insert(word)

        }
    }





}