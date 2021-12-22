package com.backend.Instagram.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class MessageRequestBody(
    @field:NotNull(message = "user id not be null")
    @field:NotBlank(message = "user id must be filled")
    var userId: String = "",
    @field:NotNull(message = "message not be null")
    @field:NotBlank(message = "message must be filled")
    var messageBody: String = "",
    @field:NotNull(message = "recipient id not be null")
    @field:NotBlank(message = "recipient id must be filled")
    var recipientId: String = "",
)
