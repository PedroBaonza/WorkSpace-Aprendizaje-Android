package com.finalretrofittest.to_dolist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finalretrofittest.to_dolist.domain.model.Task
import com.finalretrofittest.to_dolist.domain.usecase.AddTaskUseCase
import com.finalretrofittest.to_dolist.domain.usecase.DeleteTaskUseCase
import com.finalretrofittest.to_dolist.domain.usecase.GetAllTasksUseCase
import com.finalretrofittest.to_dolist.domain.usecase.UpdateTaskUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    init {
        observeTasks()
    }

    private fun observeTasks() {
        viewModelScope.launch {
            getAllTasksUseCase().collect { taskList ->
                _tasks.value = taskList
            }
        }
    }

    fun addTask(name: String) {
        if (name.isBlank()) return
        val newTask = Task(name = name)
        viewModelScope.launch {
            addTaskUseCase(newTask)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }

    fun toggleTaskDone(task: Task) {
        viewModelScope.launch {
            val updatedTask = task.copy(isDone = !task.isDone)
            updateTaskUseCase(updatedTask)
        }
    }
}