package com.example.navigationsafeargs.data.interfaces

import com.example.navigationsafeargs.data.model.TaskItem

public interface DeleteTask {
    fun deleteItem(taskItem: TaskItem)
}