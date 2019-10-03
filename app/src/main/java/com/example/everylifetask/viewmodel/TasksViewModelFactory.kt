package com.example.everylifetask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.everylifetask.repositry.TasksRepositryImplementation

class TasksViewModelFactory (val tasksRepo: TasksRepositryImplementation?) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TasksViewModel(tasksRepo!!) as T
    }
}


