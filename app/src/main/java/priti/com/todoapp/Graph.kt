package priti.com.todoapp

import android.content.Context
import priti.com.todoapp.domain.usecase.TodoDataSource
import priti.com.todoapp.data.room.TodoDatabase

object Graph {
    lateinit var database: TodoDatabase
        private set
    val todoRepo by lazy {
        TodoDataSource(database.todoDao())
    }

    fun provide(context: Context) {
        database = TodoDatabase.getDatabase(context)
    }
}