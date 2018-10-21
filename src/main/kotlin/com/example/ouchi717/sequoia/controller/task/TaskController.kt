package com.example.ouchi717.sequoia.controller.task

import com.example.ouchi717.sequoia.appservice.task.TaskDto
import com.example.ouchi717.sequoia.appservice.task.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping

/**
 * The controller class of Tasks.
 *
 * @author ouchi717
 */
@RequestMapping("/api/tasks")
@RestController
class TaskController(@Autowired var taskService: TaskService) {

    /**
     * タスクを新規登録する
     */
    @PostMapping
    fun createTask(@Validated @RequestBody requestBody: TaskCreateRequestBody): ResponseEntity<TaskDto> {
        val content: String = requireNotNull(requestBody.content)
        return ResponseEntity(taskService.createNewTask(content), HttpStatus.CREATED)
    }

    /**
     * タスクをすべて検索します.
     */
    @GetMapping
    fun searchTasks(): ResponseEntity<List<TaskDto>> {
        val tasks: List<TaskDto> = taskService.selectAllTasks()
        return ResponseEntity(tasks, HttpStatus.OK)
    }

    /**
     * タスクを完了する.
     */
    @PutMapping("/{taskId}/done")
    fun doneTask(@PathVariable taskId: Long): ResponseEntity<Nothing> {
        taskService.doneTask(taskId)
        return ResponseEntity.noContent().build()
    }

    /**
     * タスクをすべて完了する.
     */
    @PutMapping("/done")
    fun doneAllTaska(): ResponseEntity<Nothing> {
        taskService.doneAllTasks()
        return ResponseEntity.noContent().build()
    }

    /**
     * タスクを未完了にする.
     */
    @PutMapping("/{taskId}/undone")
    fun undoneTask(@PathVariable taskId: Long): ResponseEntity<Nothing> {
        taskService.undoneTask(taskId)
        return ResponseEntity.noContent().build()
    }

    /**
     * タスクをすべて未完了する.
     */
    @PutMapping("/undone")
    fun undoneAllTaska(): ResponseEntity<Nothing> {
        taskService.undoneAllTasks()
        return ResponseEntity.noContent().build()
    }
}