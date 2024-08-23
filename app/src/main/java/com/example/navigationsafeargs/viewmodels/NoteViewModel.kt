package com.example.navigationsafeargs.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationsafeargs.data.model.TaskItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.navigationsafeargs.data.repository.TaskRepository
import kotlinx.coroutines.flow.StateFlow


class NoteViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {


    // val tasks: LiveData<List<TaskItem>> = taskRepository.tasks
    val tasks: StateFlow<List<TaskItem>> = taskRepository.tasks

    fun insertTask(taskItem: TaskItem) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.insertTask(taskItem)
        }
    }

    fun deleteTask(taskItem: TaskItem) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.deleteTask(taskItem)
        }
    }

    fun updateTask(taskItem: TaskItem) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.updateTask(taskItem)
        }
    }
}
