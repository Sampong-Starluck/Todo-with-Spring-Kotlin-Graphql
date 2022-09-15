package com.sampong.graphql.controller

import com.sampong.graphql.models.Todo
import com.sampong.graphql.service.TodoService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
//import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

/**
 * Rest API annotation = [GET, PUT, POST, DELETE]
 * GraphQl API annotation = [QUERY, MUTATION]
 * */
@Controller
@RequiredArgsConstructor
//@RequestMapping("/v1/api")
class TodoController {
	@Autowired
	lateinit var todoService: TodoService
	
	@QueryMapping
//	@GetMapping("/{id}")
	fun findTodo(
	@Argument id: Long
//	@PathVariable id: Long
): Optional<Todo> = todoService.getTodo(id)
	
	@QueryMapping
//	@GetMapping("/list")
	fun todos(@Argument count: Int): List<Todo?>? {
		return todoService.getTodos(count.toLong())
	}
	
	@MutationMapping
//	@PostMapping("/create")
	fun createTodo(@Argument text: String?): Todo {
		return todoService.createTodo(text!!)
	}
	
	@MutationMapping
	fun toggleTodo(@Argument id: Long?): Todo {
		return todoService.toggleTodoById(id!!)
	}
	
	@MutationMapping
//	@DeleteMapping("/delete/{id}")
	fun deleteTodo(
	@Argument id: Long?,
//	@PathVariable id: Long
): Todo? {
		return todoService.deleteByTodoById(id!!)
	}
	
	@MutationMapping
//	@DeleteMapping("/delete/all")
	fun deleteAllTodos(): List<Todo?>? {
		return todoService.deleteAllTodo()
	}
	
}