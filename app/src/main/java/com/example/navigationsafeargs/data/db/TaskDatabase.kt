package com.example.navigationsafeargs.data.db

import com.example.navigationsafeargs.data.dao.TaskDao
import com.example.navigationsafeargs.data.model.TaskItem
import android.content.Context
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.navigationsafeargs.data.dao.DeletedTaskDao
import com.example.navigationsafeargs.data.model.DeletedTaskItem

@Database(entities = [TaskItem::class,DeletedTaskItem::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun deletedTaskDao(): DeletedTaskDao

    companion object {
        @Volatile
        private var instance: TaskDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): TaskDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TaskDatabase::class.java,
                "TaskDatabase"
            ).build()
    }
}
