package com.backend.Instagram.repository

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class FriendRequestBody(
    @field:NotBlank(message = "must be filled")
    @field:NotNull(message = "not null")
    var friendUserId: String = "",
)
