package com.example.ouchi717.sequoia.domain.task

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * The repository of the Task table.
 */
@Repository
interface TaskRepository: JpaRepository<Task, Long> {

    fun findByDoneFlgFalse(): List<Task>

    fun findByDoneFlgTrue(): List<Task>
}