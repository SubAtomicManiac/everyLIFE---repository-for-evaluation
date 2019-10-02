package com.example.everylifetask

import android.content.Context

interface TasksApiServicing {
    fun getTasks(context: Context): Array<Task>
}