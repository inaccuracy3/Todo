package work.prgrm.todolist.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TodolistWithCategoriesEntity (
    @Embedded val todoEntity: TodoEntity,
    @Relation(
        parentColumn = "todoId",
        entityColumn = "categoryId",
        associateBy = Junction(TodoWithCategoryEntity::class)
    )
    val categoryEntity: CategoryEntity
    )