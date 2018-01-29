package com.example.ouchi717.sequoia.appservice.task

import com.example.ouchi717.sequoia.appservice.shared.exception.ResourceNotFoundException
import com.example.ouchi717.sequoia.domain.task.Task
import com.example.ouchi717.sequoia.domain.task.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class TaskService(@Autowired var taskRepository: TaskRepository) {

    /**
     * タスクを新規作成します.
     */
    @Transactional(rollbackOn = [Exception::class])
    fun createNewTask(content: String): TaskDto {
        val task = Task().apply { this.content = content }
        taskRepository.save(task)
        return TaskDto(task.taskId)
    }

    /**
     * タスクをすべて検索します.
     */
    fun selectTasks(): List<TaskDto> {
        return taskRepository.findAll().map {
            TaskDto(it.taskId, it.userId, it.content, it.doneFlg)
        }
    }

    /**
     * タスクを完了します.
     *
     * @param taskId タスクID
     */
    @Transactional(rollbackOn = [Exception::class])
    fun doneTask(taskId: Long) {
        val task = findTask(taskId)
        task.done()
        taskRepository.save(task)
    }

    /**
     * タスクを未完了にします.
     *
     * @param taskId タスクID
     */
    @Transactional(rollbackOn = [Exception::class])
    fun undoneTask(taskId: Long) {
        val task = findTask(taskId)
        task.undone()
        taskRepository.save(task)
    }

    /**
     * タスクを削除にします.
     *
     * @param taskId タスクID
     */
    @Transactional(rollbackOn = [Exception::class])
    fun deleteTask(taskId: Long) {
        taskRepository.delete(findTask(taskId))
    }

    /**
     * 指定したIDのタスクを検索する。
     * タスクが存在しない場合、ResourceNotFoundException をthrowする
     *
     * @param taskId タスクID
     */
    private fun findTask(taskId: Long): Task {
        return taskRepository.findOne(taskId)?: throw ResourceNotFoundException("Task is not found.")
    }
}