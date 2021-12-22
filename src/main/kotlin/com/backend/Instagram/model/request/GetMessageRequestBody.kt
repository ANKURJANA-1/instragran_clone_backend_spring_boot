package com.backend.Instagram.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class GetMessageRequestBody(
    @field:NotNull(message = "user id not be null")
    @field:NotBlank(message = "user id must be filled")
    var userId: String = "",
    @field:NotNull(message = "recipient id not be null")
    @field:NotBlank(message = "recipient id must be filled")
    var recipientId: String = "",
)
