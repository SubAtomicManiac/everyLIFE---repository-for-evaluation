package com.example.everylifetask.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.everylifetask.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) replaceFragment()
    }

    fun replaceFragment(){
        supportFragmentManager.beginTransaction().run {
            replace(R.id.fragment_content, TasksListFragment())
            commit()
        }
    }
}
