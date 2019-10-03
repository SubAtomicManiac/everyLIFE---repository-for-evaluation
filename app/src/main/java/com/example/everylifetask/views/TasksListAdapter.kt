package com.example.everylifetask.views

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.everylifetask.R
import com.example.everylifetask.models.Task
import com.example.everylifetask.commons.TaskType
import com.example.everylifetask.commons.inflate

class TasksListAdapter(private var tasks: Array<Task>) :
    RecyclerView.Adapter<TaskViewHolder>() {

    var viewHolderPosition = 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        viewHolderPosition++
        return when (viewHolderPosition.rem(2)){
            0 -> TaskViewHolder(viewGroup.inflate(R.layout.task_row_item))
            1 -> TaskViewHolder(viewGroup.inflate(R.layout.task_row_item_border))
            else -> TaskViewHolder(viewGroup.inflate(R.layout.task_row_item))
        }
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
        return when (type) {
            TaskType.general -> ContextCompat.getDrawable(context, R.mipmap.general)
            TaskType.hydration -> ContextCompat.getDrawable(context, R.mipmap.hydration)
            TaskType.medication -> ContextCompat.getDrawable(context, R.mipmap.medication)
            TaskType.nutrition -> ContextCompat.getDrawable(context, R.mipmap.nutrition)
        }
    }

    public fun updateFilter(filteredTasks: Array<Task>) {
        tasks = filteredTasks
    }
}