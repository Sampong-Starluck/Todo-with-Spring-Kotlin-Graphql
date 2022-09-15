package com.sampong.graphql.models

import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.hibernate.Hibernate
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@Getter
@Setter
@NoArgsConstructor
class Todo(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long?= 0,
	var text: String?= null,
	private var completed: Boolean?= false,
): Serializable{
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
		other as Todo
		
		return id == other.id
	}
	
	override fun hashCode(): Int = javaClass.hashCode()
	
	@Override
	override fun toString(): String {
		return this::class.simpleName + "(id = $id , text = $text , completed = $completed )"
	}
	
	@Builder
	fun Todo(text: String){
		this.text = text
	}
	
	fun toggle(): Todo{
		completed = !completed!!
		return this
	}
}