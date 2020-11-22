package com.example.roomwordsample.data.datasource.dao

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class LocalMigrations {

    companion object{
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }

        val MIGRATION_2_3: Migration =object : Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE word_table "
                        + "ADD COLUMN last_update TEXT")
            }
        }
    }
}