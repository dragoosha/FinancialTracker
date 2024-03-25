package com.example.fintrack.data.model.accountsDb

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [AccountsDatabaseEntity::class],
    version = 1,
    autoMigrations = [
    ]
)
abstract class AccountsDatabase : RoomDatabase() {
    abstract fun getAccountsDao(): AccountsDao

    companion object {
        private var DB_INSTANCE: AccountsDatabase? = null
        private const val DATABASE_NAME = "TV_DB"

        fun getAccountsDatabaseInstance(context: Context): AccountsDatabase {
            return DB_INSTANCE ?: Room
                .databaseBuilder(
                    context.applicationContext,
                    AccountsDatabase::class.java,
                    DATABASE_NAME
                )
                .build()
                .also { DB_INSTANCE = it }
        }
    }
}