package com.halawystory.shopapp.mvvm.view.HomeScreen

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.halawystory.shopapp.mvvm.viewmodel.TodoViewModel

@Composable
fun TodoList(viewModel: TodoViewModel, context: Context){
    val todoList by viewModel.getTodoList().observeAsState()
    val listState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize().imePadding()){
        todoList?.let { todoList ->
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(todoList , key = {it.id}){todo ->
                    Spacer(modifier = Modifier.size(10.dp))
                    TodoElement(viewModel = viewModel , todo = todo , context = context)

                    Divider(
                        thickness = 0.2.dp,
                        color = Color.Gray
                    )
                }
            }
        }

    }
}
