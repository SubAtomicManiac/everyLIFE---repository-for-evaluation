package com.example.everylifetask.di

import com.example.everylifetask.api.TasksApiService
import com.example.everylifetask.commons.Application.Companion.context
import com.example.everylifetask.repositry.TasksRepositryImplementation
import com.example.everylifetask.services.TasksService
import com.example.everylifetask.viewmodel.TasksViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule() {
    @Provides
    @Singleton
    fun provideTaskServiceApi(): TasksApiService {
        return TasksApiService()
    }

    @Provides
    @Singleton
    fun provideTaskService(tasksApiService: TasksApiService): TasksService{
        return TasksService(context!!,tasksApiService)
    }

    @Provides
    @Singleton
    fun provideTasksRepositryImplementation(tasksService: TasksService) : TasksRepositryImplementation{
        return TasksRepositryImplementation(tasksService)
    }

    @Provides
    @Singleton
    fun provideTasksViewModelFactory(repositryImplementation: TasksRepositryImplementation) : TasksViewModelFactory{
        return TasksViewModelFactory(repositryImplementation)
    }
}