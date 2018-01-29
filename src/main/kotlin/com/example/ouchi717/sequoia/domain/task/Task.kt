package com.example.ouchi717.sequoia.domain.task

import com.example.ouchi717.sequoia.domain.shared.AbstractEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * The entity of Task.
 */
@Entity
@Table(name = "tasks")
data class Task(

    @Id
    @Column(name = "task_id", nullable = false)
    @GeneratedValue
    var taskId: Long = 0,

    @Column(name = "user_id", nullable = false)
    var userId: Long = 0,

    @Column(name = "content", nullable = false)
    var content: String = "",

    @Column(name = "done_flg", nullable = true)
    var doneFlg: Boolean = false

) : AbstractEntity() {

    fun done() {
        this.doneFlg = true
    }

    fun undone() {
        this.doneFlg = false
    }
}