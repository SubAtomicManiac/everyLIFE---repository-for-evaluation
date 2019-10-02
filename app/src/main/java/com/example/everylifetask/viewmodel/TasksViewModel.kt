package com.example.everylifetask.viewmodel


import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.everylifetask.R
import com.example.everylifetask.commons.TaskType
import com.example.everylifetask.models.Task
import com.example.everylifetask.api.TasksApiServicing
import com.example.everylifetask.views.TasksListFragment

class TasksViewModel(val tasksApiService: TasksApiServicing?, val fragment: TasksListFragment?) :
    ViewModel(), TasksViewModelInterface {
    var tasks: Array<Task>? = null
    var filteredTasksLiveData : MutableLiveData<Array<Task>>? = MutableLiveData<Array<Task>>()

    override fun reloadTable(context: Context) {
        tasks = tasksApiService?.getTasks(context)
        filteredTasksLiveData?.value = tasks
    }

    override fun beginRefreshing() {
//        TODO("A: implement this so that the progressBar is shown only when loading. " +
//                    "The progressBar should also be adjusted to be central at the top of the screen")
        val view1: ProgressBar? = this.fragment?.view?.findViewById<ProgressBar>(R.id.progressBar)
        view1?.visibility = View.VISIBLE
    }

    override fun endRefreshing() {
//        TODO("A: implement this so that the progressBar is shown only when loading. ")
        val view1: ProgressBar? = fragment?.view?.findViewById<ProgressBar>(R.id.progressBar)
        view1?.visibility = View.INVISIBLE
    }

    override fun getFilteredTasksData() = filteredTasksLiveData

    override fun filterClicked(tag: Any?) {
//        TODO("B: implement this so that the list will show only the tasks that have the selected TaskType")
        if (filteredTasksLiveData?.value?.get(0)?.type?.equals(tag as TaskType)!!) {
            filteredTasksLiveData?.value = tasks
        } else {
            val list =
                tasks?.filter {
                    val taskType = tag as TaskType
                    it.type.equals(taskType)
                }
            filteredTasksLiveData?.value = list?.toTypedArray()
        }
    }
}