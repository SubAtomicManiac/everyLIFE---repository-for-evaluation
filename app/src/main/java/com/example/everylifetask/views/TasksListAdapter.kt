package com.example.everylifetask.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.everylifetask.R
import com.example.everylifetask.models.Task
import com.example.everylifetask.commons.TaskType
import com.example.everylifetask.commons.inflate

class TasksListAdapter(private var tasks: Array<Task>) :
    RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(viewGroup.inflate(R.layout.task_row_item))
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(viewHolder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        viewHolder.nameLabel.text = task.name
        viewHolder.descriptionLabel.text = task.description
        viewHolder.typeIconImageView.setImageDrawable(
            getTaskTypeImage(
                task.type,
                viewHolder.itemView.context
            )
        )
    }

    private fun getTaskTypeImage(type: TaskType, context: Context): Drawable? {
        when (type) {
            TaskType.general -> {
                return ContextCompat.getDrawable(context, R.mipmap.general)
            }
            TaskType.hydration -> {
                return ContextCompat.getDrawable(context, R.mipmap.hydration)
            }
            TaskType.medication -> {
                return ContextCompat.getDrawable(context, R.mipmap.medication)
            }
            TaskType.nutrition -> {
                return ContextCompat.getDrawable(context, R.mipmap.nutrition)
            }
        }
        return null
    }

    public fun updateFilter(filteredTasks: Array<Task>) {
        tasks = filteredTasks
    }
}