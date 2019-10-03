package com.example.everylifetask.services

import com.example.everylifetask.models.Task
import io.reactivex.Observable

interface TasksServiceInterface {
    fun getTasks(): Observable<Array<Task>>
}