package com.backend.Instagram.model.error

import org.springframework.http.HttpStatus

data class GenericError(
    var message: String = "",
    var status: HttpStatus = HttpStatus.BAD_REQUEST
)
