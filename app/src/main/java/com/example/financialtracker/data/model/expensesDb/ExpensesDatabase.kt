package com.example.fintrack.data.model.expensesDb

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ExpensesDatabaseEntity::class],
    version = 1,
    autoMigrations = [
    ]
)
abstract class ExpensesDatabase : RoomDatabase() {
    abstract fun getExpensesDao(): ExpensesDao

    companion object {
        private var DB_INSTANCE: ExpensesDatabase? = null
        private const val DATABASE_NAME = "TV_DB"

        fun getExpensesDatabaseInstance(context: Context): ExpensesDatabase {
            return DB_INSTANCE ?: Room
                .databaseBuilder(
                    context.applicationContext,
                    ExpensesDatabase::class.java,
                    DATABASE_NAME
                )
                .build()
                .also { DB_INSTANCE = it }
        }
    }
}