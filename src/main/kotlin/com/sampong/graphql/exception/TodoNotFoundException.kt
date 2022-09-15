package com.sampong.graphql.exception

import graphql.GraphQLException

class TodoNotFoundException() {
	fun exception(invalidTodoId: Long?): GraphQLException{
		return GraphQLException(String.format("Todo ID: $invalidTodoId not found"))
	}
}