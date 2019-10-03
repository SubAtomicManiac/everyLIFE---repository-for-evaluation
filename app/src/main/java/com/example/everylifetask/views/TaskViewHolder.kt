package com.example.everylifetask.views

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.task_row_item.view.*

class TaskViewHolder(taskView: View) : RecyclerView.ViewHolder(taskView) {
    val nameLabel = taskView.name
    val descriptionLabel = taskView.description
    val typeIconImageView = taskView.task_type
}