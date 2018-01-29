package com.example.ouchi717.sequoia.appservice.task

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class TaskDto(
    val taskId: Long,
    val userId: Long? = null,
    val content: String? = null,
    val doneFlg: Boolean? = null
)