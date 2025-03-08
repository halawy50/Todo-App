package com.halawystory.shopapp.mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var title:String,
    var createdAt:String,
    val time: Long = System.currentTimeMillis() // تخزين الوقت كـ timestamp
)