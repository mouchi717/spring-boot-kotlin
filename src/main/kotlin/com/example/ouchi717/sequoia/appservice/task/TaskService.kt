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
    fun selectAllTasks(): List<TaskDto> {
        return taskRepository.findAll().map {
            TaskDto(it.taskId, it.userId, it.content, it.doneFlg)
        }
    }

    /**
     * 指定したタスクを完了します.
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
     * タスクをすべて完了します.
     */
    @Transactional(rollbackOn = [Exception::class])
    fun doneAllTasks() {
        val tasks: List<Task> = taskRepository.findAll()
        tasks.forEach{ it -> it.done() }
        taskRepository.save(tasks)
    }

    /**
     * 指定したタスクを未完了にします.
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
     * タスクをすべて未完了します.
     */
    @Transactional(rollbackOn = [Exception::class])
    fun undoneAllTasks() {
        val tasks: List<Task> = taskRepository.findAll()
        tasks.forEach{ it -> it.undone() }
        taskRepository.save(tasks)
    }

    /**
     * 指定したタスクを削除します.
     *
     * @param taskId タスクID
     */
    @Transactional(rollbackOn = [Exception::class])
    fun deleteTask(taskId: Long) {
        taskRepository.delete(findTask(taskId))
    }

    /**
     * 完了したタスクをすべて削除します.
     */
    @Transactional(rollbackOn = [Exception::class])
    fun deleteDoneTasks() {
        val doneTasks = taskRepository.findByDoneFlgTrue()
        taskRepository.delete(doneTasks)
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