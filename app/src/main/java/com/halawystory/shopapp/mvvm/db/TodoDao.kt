package com.halawystory.shopapp.mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.halawystory.shopapp.mvvm.model.Todo

@Dao
interface TodoDao {

//    @Query("SELECT * FROM TODO ORDER BY time ASC") // من الاقدم للاجدد
    @Query("SELECT * FROM TODO ORDER BY time DESC") // من الاجدد للاقدم
    fun getAllTodo(): LiveData<List<Todo>>

    @Insert
    fun addTodo(todo : Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTodo(todo : Todo)


    @Query("DELETE FROM TODO where id = :id")
    fun deleteTodo(id:Int)

}