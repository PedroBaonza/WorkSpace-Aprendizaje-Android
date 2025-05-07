package com.finalretrofittest.to_dolist.domain.repository

import com.finalretrofittest.to_dolist.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun addTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)

    fun getAllTasks(): Flow<List<Task>>
}