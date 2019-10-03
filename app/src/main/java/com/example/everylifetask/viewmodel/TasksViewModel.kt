package com.example.everylifetask.viewmodel


import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.everylifetask.commons.TaskType
import com.example.everylifetask.models.Task
import com.example.everylifetask.repositry.TasksRepositryImplementation

class TasksViewModel(val taskRepo: TasksRepositryImplementation) :
    ViewModel(), TasksViewModelInterface {
    var tasks: Array<Task>? = null

    var filteredTasksLiveData : MutableLiveData<Array<Task>>? = MutableLiveData<Array<Task>>()
    var isRefreshing : MutableLiveData<Boolean>? = MutableLiveData<Boolean>(false)

    override fun getFilteredTasksData() = filteredTasksLiveData
    override fun getIsRefreshing() = isRefreshing

    override fun reloadTable(context: Context) {
        Log.i("refreshing","refreshing: ${isRefreshing?.value}")
        beginRefreshing()
        Log.i("refreshing","refreshing: ${isRefreshing?.value}")
        taskRepo.getTasks(
            {
                t -> tasks = t
                filteredTasksLiveData?.value = tasks
                endRefreshing()
            },
            {t -> Log.i("uhoh","Something want wrong ${t.message}")}
        )
    }

    override fun beginRefreshing() {
        isRefreshing?.value = true
    }

    override fun endRefreshing() {
        isRefreshing?.value = false
    }

    override fun filterClicked(tag: Any?) {
        filteredTasksLiveData?.value = when (tag as TaskType){
            filteredTasksLiveData?.value?.get(0)?.type -> tasks
            else -> tasks?.filter {it.type == tag}?.toTypedArray()
        }
    }
}