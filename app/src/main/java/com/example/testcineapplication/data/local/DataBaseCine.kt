package com.example.testcineapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UsuarioEntity::class], version = 2)
abstract class DataBaseCine : RoomDatabase() {

    abstract fun loginDAO(): LoginDAO

    companion object {

        private var INSTANCE: DataBaseCine? = null

        fun getDatabase(context: Context): DataBaseCine {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                DataBaseCine::class.java,
                "movie_db"
            ).build()
            return INSTANCE!!
        }

        fun destroyDatabase() {
            INSTANCE = null
        }

    }
}