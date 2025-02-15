package priti.com.todoapp.data.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import priti.com.todoapp.domain.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun selectAll(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Query("Delete From todo WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM todo")
    suspend fun deleteAllTodo()

    @Query("UPDATE todo SET isComplete = :isComplete WHERE id = :id")
    suspend fun updateTodo(isComplete: Boolean, id: Long)


}
