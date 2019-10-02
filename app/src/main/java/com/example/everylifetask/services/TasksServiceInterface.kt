package com.example.everylifetask.services

import android.content.Context
import com.example.everylifetask.api.TasksApiServicing
import com.example.everylifetask.models.Task

interface TasksServiceInterface {
    fun getTasks(context: Context, tasksApiServicing: TasksApiServicing): Array<Task>
}