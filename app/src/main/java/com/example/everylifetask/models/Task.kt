package com.example.everylifetask.models

import com.example.everylifetask.commons.TaskType

data class Task(var id: Int, var name: String, var description: String, var type: TaskType)