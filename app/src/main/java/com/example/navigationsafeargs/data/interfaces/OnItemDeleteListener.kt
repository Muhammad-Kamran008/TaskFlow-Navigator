package com.example.navigationsafeargs.data.interfaces

import com.example.navigationsafeargs.data.model.TaskItem


interface OnItemDeleteListener {
    fun onDeleteConfirmed(taskItem: TaskItem)
}

