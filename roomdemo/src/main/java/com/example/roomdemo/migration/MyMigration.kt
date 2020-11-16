package com.example.roomdemo.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_3_4 = object : Migration(3,4){
    override fun migrate(database: SupportSQLiteDatabase) {
        TODO("Not yet implemented")
    }
}

class MyMigration(startVersion : Int, endVersion : Int) : Migration(startVersion, endVersion) {
    override fun migrate(database: SupportSQLiteDatabase) {
        TODO("Not yet implemented")
    }
}