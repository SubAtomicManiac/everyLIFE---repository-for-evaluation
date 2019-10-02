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

class TasksListAdapter(private var tasks: Array<Task>) :
    RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.task_row_item, viewGroup, false)

        return TaskViewHolder(v)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(viewHolder: TaskViewHolder, position: Int) {
        viewHolder.nameLabel.text = tasks[position].name
        viewHolder.descriptionLabel.text = tasks[position].description
        viewHolder.typeIconImageView.setImageDrawable(
            getTaskTypeImage(
                tasks[position].type,
                viewHolder.itemView.context
            )
        )
    }

    private fun getTaskTypeImage(type: TaskType, context: Context): Drawable? {
        when (type) {
            TaskType.GENERAL -> {
                return ContextCompat.getDrawable(context, R.mipmap.general)
            }
            TaskType.HYDRATION -> {
                return ContextCompat.getDrawable(context, R.mipmap.hydration)
            }
            TaskType.MEDICATION -> {
                return ContextCompat.getDrawable(context, R.mipmap.medication)
            }
            TaskType.NUTRITION -> {
                return ContextCompat.getDrawable(context, R.mipmap.nutrition)
            }
        }
        return null
    }

    public fun updateFilter(filteredTasks: Array<Task>) {
        tasks = filteredTasks
    }
}