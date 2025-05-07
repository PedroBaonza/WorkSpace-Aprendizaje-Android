package com.finalretrofittest.to_dolist.domain.usecase

import com.finalretrofittest.to_dolist.domain.model.Task
import com.finalretrofittest.to_dolist.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetAllTasksUseCase(private val repository: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> {
        return repository.getAllTasks()
    }
}