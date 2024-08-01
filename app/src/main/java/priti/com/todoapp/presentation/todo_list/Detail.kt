package priti.com.todoapp.presentation.todo_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import priti.com.todoapp.domain.model.Todo
import priti.com.todoapp.presentation.todo_update.DetailViewModel
import priti.com.todoapp.presentation.todo_update.DetailViewModelFactory

@Composable
fun DetailScreen(
    selectedId: Long,
    onNavigate: () -> Unit,
) {
    val viewModel = viewModel(
        DetailViewModel::class.java,
        factory = DetailViewModelFactory(selectedId)
    )
    val state by viewModel.state.collectAsState()
    DetailScreenComponent(todoText = state.todo,
        onTodoTextChange = { viewModel.onTextChange(it) },
        descriptionText = state.time,
        onDescriptionChange = { viewModel.onTimeChange(it) },
        onNavigate = { onNavigate() },
        onSaveTodo = { viewModel.insert(it) },
        selectedId = state.selectId)
}

@Composable
fun DetailScreenComponent(
    todoText: String,
    onTodoTextChange: (String) -> Unit,
    descriptionText: String,
    onDescriptionChange: (String) -> Unit,
    onNavigate: () -> Unit,
    onSaveTodo: (Todo) -> Unit,
    selectedId: Long,
) {
    val isTodoEdit = selectedId == -1L
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = todoText,
            onValueChange = { onTodoTextChange(it) },
            label = { Text(text = "Enter Todo") }
        )
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = descriptionText,
            onValueChange = { onDescriptionChange(it) },
            label = { Text(text = "Enter Description") }
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            val todo = if (isTodoEdit) Todo(todoText, descriptionText)
            else Todo(todoText, descriptionText, id = selectedId)
            onSaveTodo(todo)
            onNavigate()
        }) {
            val text = if (isTodoEdit) "Save Todo" else "Update todo"
            Text(text = text)
        }


    }


}













