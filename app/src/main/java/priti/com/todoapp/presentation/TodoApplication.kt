package priti.com.todoapp.presentation

import android.app.Application
import priti.com.todoapp.Graph

class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}