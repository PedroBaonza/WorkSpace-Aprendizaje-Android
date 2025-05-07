package com.finalretrofittest.to_dolist.domain.usecase

import com.finalretrofittest.to_dolist.domain.model.Task
import com.finalretrofittest.to_dolist.domain.repository.TaskRepository

class UpdateTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        repository.updateTask(task)
    }
}