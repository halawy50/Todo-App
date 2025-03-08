package com.halawystory.shopapp.mvvm.view.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.GifDecoder
import coil3.request.ImageRequest
import com.halawystory.shopapp.R

//Gif
@Composable
fun GifFromDrawable() {
    val context = LocalContext.current

    // إعداد ImageLoader مخصص لدعم GIF
    val imageLoader = ImageLoader.Builder(context)
        .components { add(GifDecoder.Factory()) } // تفعيل دعم GIF
        .build()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(R.drawable.empty_todo) // اسم ملف GIF في drawable
                .build(),
            contentDescription = "Animated GIF",
            modifier = Modifier
                .fillMaxWidth(),
            imageLoader = imageLoader // تمرير ImageLoader المخصص

        )
        Text(text = "Empty List\nadd new note", textAlign = TextAlign.Center)
    }

}
