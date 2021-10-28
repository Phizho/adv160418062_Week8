package com.ubaya.todoapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ubaya.todoapp.Model.Todo
import com.ubaya.todoapp.Model.TodoDatabase
import com.ubaya.todoapp.Util.buildDb
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())

            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    fun clearTask(todo: Todo) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().checked(1, todo.uuid)

            todoLD.value = db.todoDao().selectAllTodo()
        }
    }
}

