package com.halawystory.shopapp.mvvm.view.HomeScreen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.halawystory.shopapp.mvvm.viewmodel.TodoViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.halawystory.shopapp.R
import com.halawystory.shopapp.mvvm.model.Todo
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Composable
@SuppressLint("NewApi", "SuspiciousIndentation")
fun TodoElement(viewModel: TodoViewModel,todo:Todo , context: Context){
    var updateTodo by remember {
        mutableStateOf(false)
    }
    var titleTodo by remember {
        mutableStateOf(todo.title)
    }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(0.95f)
            ) {
                //show Todo
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    if(updateTodo==false){
                        Text(text = titleTodo.toString(), style = TextStyle(color = Color.Black, fontWeight = FontWeight.Normal))
                    }else{
                        OutlinedTextField(value = titleTodo, onValueChange = {titleTodo = it})
                    }

                    Spacer(modifier = Modifier.size(10.dp))

                    Text(text = todo.createdAt,
                        style = TextStyle(color = Color.Gray,
                            fontWeight = FontWeight.Normal,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                        )
                }
                Spacer(modifier = Modifier.size(10.dp))
                if (updateTodo==true){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        //Update
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if(titleTodo.isNullOrEmpty()) Color.Gray else Color.Blue,
                                contentColor = Color.White
                            ),
                            onClick = {
                                if(titleTodo.isNullOrEmpty()){
                                    Toast.makeText(context,"add note",Toast.LENGTH_SHORT).show()
                                }else{
                                    val now = Instant.now().atZone(ZoneId.systemDefault()) // تحويل إلى المنطقة الزمنية المحلية
                                    val formatter = DateTimeFormatter.ofPattern("HH:MM yyyy-MM-dd")

                                    val formattedDate = now.format(formatter)


                                    var todo = Todo(
                                        id = todo.id,
                                        title = titleTodo ,
                                        createdAt = formattedDate.toString()
                                    )
                                    viewModel.updateTodo(todo)
                                    updateTodo = false
                                }
                            }) {
                            Text(text = "Update Note", style = TextStyle(color = Color.White))
                        }
                        Spacer(modifier = Modifier.size(5.dp))

                        //Cancel
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor =  Color.Red,
                                contentColor = Color.White
                            ),
                            onClick = {
                                updateTodo = false
                                titleTodo = todo.title
                            }) {
                            Text(text = "Cancel", style = TextStyle(color = Color.White))
                        }

                    }
                }


            }

            Row(
                Modifier
                    .weight(0.5f)
                    .fillMaxWidth()) {

                //Icon Update
                if(updateTodo==false)
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        updateTodo = true
                    }) {
                    Icon(
                        painter = painterResource(R.drawable.update_icon) ,
                        contentDescription = "update" ,
                        tint = Color.Blue
                    )
                }

                //Icon Delete Todo
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.deleteTodo(todo.id)
                    }) {
                    Icon(
                        painter = painterResource(R.drawable.delete_icon) ,
                        contentDescription = "delete Todo" ,
                        tint = Color.Red
                    )
                }
            }


        }

}