package com.example.everylifetask.api

import android.content.Context
import com.example.everylifetask.models.Task

interface TasksApiServicing {
    fun getTasks(context: Context): Array<Task>
}