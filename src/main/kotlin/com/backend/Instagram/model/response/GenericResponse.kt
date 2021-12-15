package com.backend.Instagram.model.response

import org.springframework.http.HttpStatus

data class GenericResponse(
    var message: String = "",
    var status: HttpStatus = HttpStatus.OK,
    var body: Any? = null
)
