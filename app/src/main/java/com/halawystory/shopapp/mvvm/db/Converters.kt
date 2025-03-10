package com.halawystory.shopapp.mvvm.db

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun fromData(date:Date) : Long{
        return date.time
    }

    @TypeConverter
    fun toDate(time:Long) : Date{
        return Date(time)
    }
}