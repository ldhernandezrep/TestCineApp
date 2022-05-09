package com.example.testcineapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UsuarioEntity::class, MediaEntity::class, MovieEntity::class, RoutesEntity::class],
    version = 4
)
abstract class DataBaseCine : RoomDatabase() {

    abstract fun loginDAO(): LoginDAO
    abstract fun mediaDAO(): MediaDAO
    abstract fun movieDAO(): MovieDAO
    abstract fun routeDAO(): RoutesDAO

    companion object {

        private var INSTANCE: DataBaseCine? = null

        fun getDatabase(context: Context): DataBaseCine {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                DataBaseCine::class.java,
                "movie_db"
            ).fallbackToDestructiveMigration().build()
            return INSTANCE!!
        }

        fun destroyDatabase() {
            INSTANCE = null
        }

    }
}