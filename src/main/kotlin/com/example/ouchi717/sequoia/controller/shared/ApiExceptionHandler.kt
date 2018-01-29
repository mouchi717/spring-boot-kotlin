package com.example.ouchi717.sequoia.controller.shared

import com.example.ouchi717.sequoia.appservice.shared.dto.ErrorDto
import com.example.ouchi717.sequoia.appservice.shared.dto.ErrorType
import com.example.ouchi717.sequoia.appservice.shared.exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrorDto> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDto(ErrorType.BAD_REQUEST, "JSON parse error."))
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(ex: MethodArgumentTypeMismatchException): ResponseEntity<ErrorDto> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDto(ErrorType.BAD_REQUEST, "${ex.name} should be of type ${ex.requiredType.name}."))
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorDto> {
        val message = ex.bindingResult.fieldErrors.joinToString { fieldError -> fieldError.defaultMessage }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDto(ErrorType.VALIDATION, message))
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(ex: HttpRequestMethodNotSupportedException): ResponseEntity<ErrorDto> {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ErrorDto(ErrorType.BAD_REQUEST, ex.message))
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleEntityNotFoundException(ex: ResourceNotFoundException): ResponseEntity<ErrorDto> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto(ErrorType.NOT_FOUND, ex.message))
    }
}