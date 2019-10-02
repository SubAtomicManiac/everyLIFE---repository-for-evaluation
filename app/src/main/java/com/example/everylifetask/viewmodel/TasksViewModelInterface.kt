package com.example.everylifetask.viewmodel


import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.everylifetask.models.Task

interface TasksViewModelInterface {
    fun reloadTable(context: Context)
    fun beginRefreshing()
    fun endRefreshing()
    fun getFilteredTasksList(): Array<Task>?
    fun filterClicked(tag: Any?)
    fun getFilteredTasksData() : MutableLiveData<Array<Task>>?
}