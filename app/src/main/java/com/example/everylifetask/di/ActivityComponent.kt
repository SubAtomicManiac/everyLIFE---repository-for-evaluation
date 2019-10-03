package com.example.everylifetask.di

import com.example.everylifetask.views.TasksListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(DataModule::class))
interface ActivityComponent {
    fun inject(tasksListFragment: TasksListFragment)
}