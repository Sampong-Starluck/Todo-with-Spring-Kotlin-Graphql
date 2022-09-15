package com.sampong.graphql.service

import com.sampong.graphql.models.Todo
import java.util.Optional

interface TodoService {
	fun createTodo(text: String): Todo
	fun deleteAllTodo(): List<Todo>
	fun deleteByTodoById(id: Long): Todo
	fun deleteTodo(todo:Todo): Todo
	fun toggleTodoById(id: Long): Todo
	fun getTodo(id: Long): Optional<Todo>
	fun getTodos(limit: Long): List<Todo>
	
}