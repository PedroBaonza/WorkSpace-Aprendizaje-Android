package com.finalretrofittest.to_dolist.data.local

import com.finalretrofittest.to_dolist.domain.model.Task

fun TaskEntity.toDomain(): Task {
    return Task(
        id = id,
        name = name,
        isDone = isDone
    )
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        name = name,
        isDone = isDone
    )
}