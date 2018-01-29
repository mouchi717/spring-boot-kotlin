package com.example.ouchi717.sequoia.domain.shared

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
@Suppress("unused")
class LocalDateTimeConverter: AttributeConverter<LocalDateTime, Timestamp> {

    override fun convertToDatabaseColumn(localDateTime: LocalDateTime?): Timestamp? {
        return Timestamp.valueOf(localDateTime ?: return null)
    }

    override fun convertToEntityAttribute(timestamp: Timestamp?): LocalDateTime? {
        return timestamp?.toLocalDateTime()
    }
}