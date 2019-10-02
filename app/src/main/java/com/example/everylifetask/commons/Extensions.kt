package com.example.everylifetask.commons

import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attatchToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes,this,attatchToRoot)
}
