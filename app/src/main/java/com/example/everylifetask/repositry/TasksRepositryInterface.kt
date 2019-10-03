package com.example.everylifetask.repositry

import com.example.everylifetask.models.Task

interface TasksRepositryInterface {
    fun getTasks(callback: (t: Array<Task>?) -> Unit, error: (t: Throwable) -> Unit)
}

