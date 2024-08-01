package priti.com.todoapp.presentation.todo_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.lifecycle.viewmodel.compose.viewModel
import priti.com.todoapp.presentation.todo_list.components.TodoItem
import priti.com.todoapp.presentation.todo_update.HomeViewModel
import priti.com.todoapp.domain.model.Todo


@Composable
fun HomeScreen(onNavigate: (Todo?) -> Unit) {
    val viewModel = viewModel(HomeViewModel::class.java)
    val state by viewModel.state.collectAsState()

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onNavigate(null) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) {
        LazyColumn {
            items(state.todoList) { todo ->
                TodoItem(
                    todo = todo,
                    onChecked = { viewModel.updateTodo(it, todo.id) },
                    onDelete = { viewModel.delete(it) },
                    onNavigation = { onNavigate(it) }
                )
            }
        }

    }


}
