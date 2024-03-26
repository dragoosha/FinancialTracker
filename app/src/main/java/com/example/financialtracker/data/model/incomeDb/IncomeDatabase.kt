package com.example.financialtracker.data.model.incomeDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [IncomeDatabaseEntity::class],
    version = 1,
    autoMigrations = [
    ]
)
abstract class IncomeDatabase : RoomDatabase() {
    abstract fun getIncomeDao(): IncomeDao

    companion object {
        private var DB_INSTANCE: IncomeDatabase? = null
        private const val DATABASE_NAME = "income_database"

        fun getIncomeDatabaseInstance(context: Context): IncomeDatabase {
            return DB_INSTANCE ?: Room
                .databaseBuilder(
                    context.applicationContext,
                    IncomeDatabase::class.java,
                    DATABASE_NAME
                )
                .build()
                .also { DB_INSTANCE = it }
        }
    }
}
