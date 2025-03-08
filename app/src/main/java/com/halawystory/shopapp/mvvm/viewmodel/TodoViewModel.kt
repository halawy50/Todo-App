package com.halawystory.shopapp.mvvm.viewmodel

import android.annotation.SuppressLint
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halawystory.shopapp.ApplicationTodo
import com.halawystory.shopapp.mvvm.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

class TodoViewModel: ViewModel() {
    val todoDao = ApplicationTodo.todoDatabase.getTodoDao()

    private val _inputTodo = MutableStateFlow("")
    val inputTodo :StateFlow<String> = _inputTodo

    fun saveNoteState(note : String){
        _inputTodo.value = note
    }
    fun getTodoList():LiveData<List<Todo>>{
        return todoDao.getAllTodo()
    }

    @SuppressLint("NewApi")
    fun addTodo(title:String){
        val now = Instant.now().atZone(ZoneId.systemDefault()) // تحويل إلى المنطقة الزمنية المحلية
        val formatter = DateTimeFormatter.ofPattern("HH:MM yyyy-MM-dd")

        val formattedDate = now.format(formatter)
        viewModelScope.launch(Dispatchers.IO){
            todoDao.addTodo(Todo(title=title , createdAt = formattedDate))
        }
    }

    fun deleteTodo(id:Int){
        viewModelScope.launch(Dispatchers.IO){
            todoDao.deleteTodo(id = id)
        }
    }

    fun updateTodo(todo : Todo){
        viewModelScope.launch(Dispatchers.IO){
            todoDao.updateTodo(todo)
        }
    }
}