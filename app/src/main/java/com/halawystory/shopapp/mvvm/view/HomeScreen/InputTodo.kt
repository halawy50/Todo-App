package com.halawystory.shopapp.mvvm.view.HomeScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halawystory.shopapp.mvvm.viewmodel.TodoViewModel

@Composable
fun InputTodo(viewModel: TodoViewModel, context : Context){

    val inputText by viewModel.inputTodo.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        OutlinedTextField(
            modifier = Modifier.weight(0.9f),
            value = inputText,
            placeholder = { Text(text = "add note", style = TextStyle(color = Color(0xFFACACAC))) },
            onValueChange = {
                viewModel.saveNoteState("${it}")
            })
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            modifier = Modifier
                .weight(0.5f)
                .height(60.dp),
            shape = RoundedCornerShape(10.dp),

            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = if (inputText.isNullOrEmpty()) Color(0xFFACACAC) else Color.Blue
            ),
            onClick = {
                if (inputText.isNullOrEmpty()){
                    Toast.makeText(context,"please add note", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.addTodo(inputText)
                    viewModel.saveNoteState("")
                }
            }) {
            Text(text = "Add", style = TextStyle(
                fontSize = 15.sp
            )
            )
        }

    }

}
