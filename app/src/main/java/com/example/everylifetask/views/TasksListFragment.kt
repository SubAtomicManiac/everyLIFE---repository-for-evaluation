package com.example.everylifetask.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.everylifetask.*
import com.example.everylifetask.commons.LayoutManagerType
import com.example.everylifetask.commons.TaskType
import com.example.everylifetask.di.DaggerActivityComponent
import com.example.everylifetask.di.DataModule
import com.example.everylifetask.models.Task
import com.example.everylifetask.viewmodel.TasksViewModel
import com.example.everylifetask.viewmodel.TasksViewModelFactory
import kotlinx.android.synthetic.main.tasks_list_fragment.*
import javax.inject.Inject

class TasksListFragment : Fragment(), LifecycleOwner{
    init {
        DaggerActivityComponent.builder()
            .dataModule(DataModule())
            .build()
            .inject(this)
    }
    @Inject
    lateinit var tasksViewModelFactory: TasksViewModelFactory
    lateinit var tasksViewModel: TasksViewModel

    lateinit var currentLayoutManagerType: LayoutManagerType
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    var layoutAdapter: TasksListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasksViewModel = ViewModelProviders
            .of(this, tasksViewModelFactory)
            .get(TasksViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.tasks_list_fragment, container, false).apply { tag =
            TAG
        }

        recyclerView = rootView.findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(activity)

        currentLayoutManagerType =
            LayoutManagerType.LINEAR_LAYOUT_MANAGER

        if (savedInstanceState != null) {
            currentLayoutManagerType = savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER) as LayoutManagerType
        }
        setRecyclerViewLayoutManager(currentLayoutManagerType)

        context?.let {
            tasksViewModel.getFilteredTasksData()?.let{
                it.observe(this@TasksListFragment, Observer<Array<Task>>{
                    tasks -> if (layoutAdapter == null){
                        layoutAdapter = TasksListAdapter(tasks)
                        recyclerView.adapter = layoutAdapter
                    } else {
                        layoutAdapter?.updateFilter(tasks)
                        layoutAdapter?.notifyDataSetChanged()
                    }
                })
            }
            tasksViewModel.getIsRefreshing()?.let{
                it.observe(this@TasksListFragment, Observer<Boolean>{
                    refreshing -> if (refreshing){
                        progressBar.visibility = View.VISIBLE
                    } else {
                        progressBar.visibility = View.GONE
                    }
                    Log.i("refreshing", "refreshing from observer $refreshing")
                })
            }
            tasksViewModel.reloadTable(it)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filter_general.setOnClickListener {
            tasksViewModel.filterClicked(TaskType.general)
        }
        filter_medication.setOnClickListener {
            tasksViewModel.filterClicked(TaskType.medication)
        }
        filter_hydration.setOnClickListener {
            tasksViewModel.filterClicked(TaskType.hydration)
        }
        filter_nutrition.setOnClickListener {
            tasksViewModel.filterClicked(TaskType.nutrition)
        }
    }

    private fun setRecyclerViewLayoutManager(layoutManagerType: LayoutManagerType) {
        var scrollPosition = 0

        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }

        when (layoutManagerType) {
            LayoutManagerType.GRID_LAYOUT_MANAGER -> {
                layoutManager = GridLayoutManager(
                    activity,
                    SPAN_COUNT
                )
                currentLayoutManagerType =
                    LayoutManagerType.GRID_LAYOUT_MANAGER
            }
            LayoutManagerType.LINEAR_LAYOUT_MANAGER -> {
                layoutManager = LinearLayoutManager(activity)
                currentLayoutManagerType =
                    LayoutManagerType.LINEAR_LAYOUT_MANAGER
            }
        }

        with(recyclerView) {
            layoutManager = this@TasksListFragment.layoutManager
            scrollToPosition(scrollPosition)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_LAYOUT_MANAGER,currentLayoutManagerType)
    }

    companion object {
        private val TAG = "TasksListFragment"
        private val KEY_LAYOUT_MANAGER = "layoutManager"
        private val SPAN_COUNT = 2
    }
}