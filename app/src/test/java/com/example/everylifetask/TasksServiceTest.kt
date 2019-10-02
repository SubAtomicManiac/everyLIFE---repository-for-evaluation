package com.example.everylifetask

import com.example.everylifetask.commons.TaskType
import com.example.everylifetask.models.Task
import com.example.everylifetask.services.TasksService
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner::class)
class TasksServiceTest {
    // TODO B: Add tests for your implementation of `getTasks` in TaskService
    val t1 = Task(42, "name", "desc", TaskType.MEDICATION)
    var t = TasksService()
        .getTasks(androidx.test.core.app.ApplicationProvider.getApplicationContext(), TasksViewModelTest.MockTasksService(arrayOf(t1)))

    @Test
    fun testTasksService() {
        assert(t.size.equals(1))
    }
}