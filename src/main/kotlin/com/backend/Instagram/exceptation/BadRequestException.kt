package com.backend.Instagram.exceptation

import org.springframework.http.HttpStatus

data class BadRequestException(
    val msg: String = "",
    val status: HttpStatus = HttpStatus.BAD_REQUEST
) : RuntimeException(msg)