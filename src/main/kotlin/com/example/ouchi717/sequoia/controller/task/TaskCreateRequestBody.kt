package com.example.ouchi717.sequoia.controller.task

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotBlank

/**
 * タスク作成用Formクラス
 *
 * @author ouchi717
 */
data class TaskCreateRequestBody(

    // タスク内容
    @get:NotBlank(message = "タスク内容は必須です")
    @get:Length(max = 140, message = "タスク内容は140文字以内で入力してください")
    val content: String? = null
)