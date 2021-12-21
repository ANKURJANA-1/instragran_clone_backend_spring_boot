package com.backend.Instagram.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class SeenStatusRequestBody(
    @field:NotBlank(message = "user id must be filled")
    @field:NotNull(message = "user id must not be null")
    var userId: String = "",

    @field:NotBlank(message = "status id must be filled")
    @field:NotNull(message = "status id must not be null")
    var statusId: String = "",
)
