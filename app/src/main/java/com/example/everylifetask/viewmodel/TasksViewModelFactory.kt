package com.example.everylifetask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.everylifetask.api.TasksApiServicing
import com.example.everylifetask.views.TasksListFragment

class TasksViewModelFactory(
    val tasksApiService: TasksApiServicing?,
    val fragment: TasksListFragment?) :
    ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TasksViewModel(tasksApiService, fragment) as T
    }
}


