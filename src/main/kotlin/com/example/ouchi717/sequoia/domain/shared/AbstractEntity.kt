package com.example.ouchi717.sequoia.domain.shared

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

/**
 * Entity共通Field.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@Suppress("unused")
abstract class AbstractEntity {

    @CreatedDate
    @Column(name = "ins_datetime", nullable = false, updatable = false)
    @JsonIgnore
    var insDatetime: LocalDateTime = LocalDateTime.now()

    @CreatedBy
    @Column(name = "ins_trace", nullable = false)
    @JsonIgnore
    var insTrace: String = ""

    @LastModifiedDate
    @Column(name = "upd_datetime", nullable = false)
    @JsonIgnore
    var updDatetime: LocalDateTime = LocalDateTime.now()

    @LastModifiedBy
    @Column(name = "upd_trace", nullable = false)
    @JsonIgnore
    var updTrace: String = ""
}