package com.example.everylifetask.services

import android.content.Context
import com.example.everylifetask.api.TasksApiServicing
import com.example.everylifetask.models.Task
import io.reactivex.Observable

class TasksService(val context: Context, val tasksApiServicing: TasksApiServicing) : TasksServiceInterface {
    override fun getTasks(): Observable<Array<Task>> {
        return Observable.fromCallable({tasksApiServicing.getTasks(context)})
    }
}