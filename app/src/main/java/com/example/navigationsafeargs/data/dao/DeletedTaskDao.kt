package com.example.navigationsafeargs.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.navigationsafeargs.data.model.DeletedTaskItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DeletedTaskDao {
    @Insert
    suspend fun insert(deletedTask: DeletedTaskItem)

    @Query("SELECT * FROM deleted_tasks")
    fun getAllDeletedTasks(): Flow<List<DeletedTaskItem>>
}
