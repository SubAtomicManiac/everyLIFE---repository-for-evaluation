package com.example.everylifetask.repositry

import com.example.everylifetask.models.Task
import com.example.everylifetask.services.TasksService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TasksRepositryImplementation(val tasksService: TasksService) : TasksRepositryInterface {
    val compositeDisposable = CompositeDisposable()
    override fun getTasks(callback: (t: Array<Task>?) -> Unit, error: (t: Throwable) -> Unit) {
        compositeDisposable.add(
            tasksService.getTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback,error)
        )
    }
}