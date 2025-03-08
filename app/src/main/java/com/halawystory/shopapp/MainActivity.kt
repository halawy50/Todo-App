package com.halawystory.shopapp

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

import com.halawystory.shopapp.mvvm.view.HomeScreen.InputTodo
import com.halawystory.shopapp.mvvm.view.HomeScreen.TodoList
import com.halawystory.shopapp.mvvm.viewmodel.TodoViewModel
import com.halawystory.shopapp.ui.theme.ShopAppTheme

import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.GifDecoder
import coil3.request.ImageRequest
import com.halawystory.shopapp.mvvm.view.HomeScreen.GifFromDrawable

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        setContent {

                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                            color = Color.White
                    ){
                        mainTodoApp(todoViewModel , this@MainActivity)
                    }
                }
            }
        }
}



@Composable
fun mainTodoApp(todoViewModel: TodoViewModel , context: Context){
    val todoList by todoViewModel.getTodoList().observeAsState()

    Column(
        verticalArrangement = Arrangement.Top ,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(10.dp)
    ) {
        Spacer(modifier = Modifier.size(10.dp))

        InputTodo(viewModel = todoViewModel,context)

        Spacer(modifier = Modifier.size(30.dp))
        if (!todoList.isNullOrEmpty())
            Text(text = "All List", style = TextStyle(color = Color.Black , fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.size(10.dp))

        if (todoList.isNullOrEmpty())
            GifFromDrawable()

        else{
            TodoList(viewModel = todoViewModel , context = context)
        }

    }
}
