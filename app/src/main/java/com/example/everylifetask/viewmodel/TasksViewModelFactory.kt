package com.example.everylifetask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.everylifetask.api.TasksApiServicing

class TasksViewModelFactory(val tasksApiService: TasksApiServicing?) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TasksViewModel(tasksApiService) as T
    }
}


