package com.example.navigationsafeargs.data.repository


import com.example.navigationsafeargs.data.dao.TaskDao
import com.example.navigationsafeargs.data.model.TaskItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskRepository(private val taskDao: TaskDao) {

    // val tasks = taskDao.getAllTasksLiveData()

    val tasks: StateFlow<List<TaskItem>> = MutableStateFlow(emptyList())

    init {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.getAllTasksFlow().collect { taskList ->
                (tasks as MutableStateFlow).value = taskList
            }
        }
    }

    suspend fun insertTask(taskItem: TaskItem) {
        withContext(Dispatchers.IO) {
            taskDao.insertTaskItem(taskItem)
        }
    }

    suspend fun deleteTask(taskItem: TaskItem) {
        withContext(Dispatchers.IO) {
            taskDao.deleteItem(taskItem)
        }
    }

    suspend fun updateTask(taskItem: TaskItem) {
        withContext(Dispatchers.IO) {
            taskDao.updateTask(taskItem)
        }
    }
}
