package br.com.zc.businesscard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [BusinessCard::class],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {

    abstract fun businessDao() : BusinessCardDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instant = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "businesscard_db"
                ).build()
                INSTANCE = instant
                instant
            }
        }
    }
}