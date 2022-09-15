package com.sampong.graphql.service.implement

import com.sampong.graphql.exception.TodoNotFoundException
import com.sampong.graphql.models.Todo
import com.sampong.graphql.repository.TodoRepository
import com.sampong.graphql.service.TodoService
import com.sampong.graphql.util.Logger
import graphql.GraphQLException
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.function.Supplier
import java.util.stream.Collectors.toList

@Service
@Slf4j
@RequiredArgsConstructor
class TodoServiceImpl : TodoService {
	@Autowired
	lateinit var todoRepository: TodoRepository
	
	private val log = Logger()
	
	@Transactional
	override fun createTodo(text: String): Todo {
		log.logInfo("CreateTodo ($text) called")
		val result = Todo()
		result.apply {
			this.text = text
		}
		return todoRepository.save(result)
	}
	
	@Transactional
	override fun deleteAllTodo(): List<Todo> {
		log.logInfo("deleteAllTodo() called")
		val todoList: List<Todo> = getTodos(0L)
		todoRepository.deleteAll()
		return todoList
	}
	
	override fun deleteByTodoById(id: Long): Todo {
		log.logInfo("deleteTodo($id) called")
		return deleteTodo(
			getTodo(id).orElseThrow(
				buildNotFoundException(id)
			)
		)
	}
	
	private fun buildNotFoundException(id: Long): Supplier<GraphQLException> {
		log.logError("Cannot locate ID: $id in database")
		return Supplier { TodoNotFoundException().exception(id) }
	}
	
	override fun deleteTodo(todo: Todo): Todo {
		todoRepository.delete(todo)
		return todo
	}
	
	@Transactional
	override fun toggleTodoById(id: Long): Todo {
		log.logDebug("toggleTodoCompleteById($id) called")
		return getTodo(id).orElseThrow(
			buildNotFoundException(id)
		).toggle()
	}
	
	@Transactional(readOnly = true)
	override fun getTodo(id: Long): Optional<Todo> {
		log.logInfo("Get todo($id) called via getTodo()")
		return todoRepository.findById(id)
	}
	
	@Transactional(readOnly = true)
	override fun getTodos(limit: Long): List<Todo> {
		log.logInfo("Get All todo with limit to $limit via getTodos")
		return todoRepository.findAll()
			.stream()
			.limit(if (limit > 0) limit else Long.MAX_VALUE)
			.collect(toList())
	}
}