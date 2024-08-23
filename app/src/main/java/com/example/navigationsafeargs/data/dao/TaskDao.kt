package com.example.navigationsafeargs.data.dao

import androidx.lifecycle.LiveData
import com.example.navigationsafeargs.data.model.TaskItem

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTaskItem(taskItem: TaskItem)

    @Query("SELECT * FROM TaskItem ORDER BY id DESC")
    //fun getAllTasksLiveData(): LiveData<List<TaskItem>> // Return LiveData for observing changes
    fun getAllTasksFlow(): Flow<List<TaskItem>>

    @Delete
    suspend fun deleteItem(taskItem: TaskItem)

    @Update
    suspend fun updateTask(taskItem: TaskItem)
}



