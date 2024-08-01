package priti.com.todoapp.domain.usecase

import priti.com.todoapp.data.room.TodoDao
import kotlinx.coroutines.Dispatchers
import priti.com.todoapp.domain.model.Todo

class TodoDataSource(private val todoDao: TodoDao) {
    val selectAll = todoDao.selectAll()

    suspend fun insertTodo(todo: Todo) {
        Dispatchers.IO.apply {
            todoDao.insert(todo)
        }
    }

    suspend fun deleteTodo(todo: Todo) {
        Dispatchers.IO.apply {
            todoDao.delete(todo.id)
        }
    }

    suspend fun updateTodo(isComplete: Boolean, id: Long) {
        Dispatchers.IO.apply {
            todoDao.updateTodo(isComplete, id)
        }
    }


}