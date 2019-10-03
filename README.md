Improvements that were implemented:

    Created packages to organise code
    Separated enum for layout manager into separate file
    Separated service implementation and interface into separate files
 
    fixed configuration change bug by add currentLayoutManagerType to savedInstanceState
 
    Created method for replacing fragment
    Created extension function for inflater in adapter.onCreateViewHolder
    Added another layout to alternate inflating of the viewgroup
    Cached task in onBindViewHolder to clean up code
    Used when return in getTaskTypeImage to clean up code
    Used kotlin syntax to create fields in one line in ViewHolder
 
    Moved majority of code from onCreate to onViewCreated in order to take advantage of synthetics
    Used when return in filterClicked
    Implemented Android ViewModel to provide a viewmodel that survives configuration change
    Implemented live data to update recyclerView and progressBar
    Implement repository pattern to use in tandem with our service
    Used ViewModelProvider to provide the repository to the viewModel
 
    Implemented asynchronous execution on getTasts using RxJava 
    Created an Application context provider
    Used Dagger2 for dependency Injection
 
    Made the UI look in parody with the design.png
    Fixed missing constraints and extracted dimension values
    Cleaned up design
    Added landscape layout variant to take advantage of screen dimensions

Improvements still to make:

    Create a view to display errors to user when getTasks fails
    Use observers or named functions/methods to handle callback on subscribe method of getTasks()

Tests to Implement:

    Use mockito, RxMocks and Junit to implement the following unit tests:

        reloadTable
        filterClicked
        getTasks

    Use Instrumentation tests:
    
        To test configuration test
        To test clicking on filter item
        Combination of configuration and clicking on filter item
    
