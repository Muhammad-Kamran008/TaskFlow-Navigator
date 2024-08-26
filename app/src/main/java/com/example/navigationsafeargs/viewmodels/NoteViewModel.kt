package com.example.navigationsafeargs.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationsafeargs.data.model.DeletedTaskItem
import com.example.navigationsafeargs.data.model.TaskItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.navigationsafeargs.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class NoteViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {


    // val tasks: LiveData<List<TaskItem>> = taskRepository.tasks
    val tasks: StateFlow<List<TaskItem>> = taskRepository.tasks

    // Deleted tasks flow
    val deletedTasks: Flow<List<DeletedTaskItem>> = taskRepository.getAllDeletedTasks()


    fun insertTask(taskItem: TaskItem) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.insertTask(taskItem)
        }
    }

    fun deleteTask(taskItem: TaskItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val deletedTask = DeletedTaskItem(
                taskname = taskItem.taskname,
                taskDescription = taskItem.taskDescription,
                priority = taskItem.priority
            )
            taskRepository.deleteTask(taskItem)
            taskRepository.insertDeletedTask(deletedTask)
        }
    }

    fun updateTask(taskItem: TaskItem) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.updateTask(taskItem)
        }
    }
}
