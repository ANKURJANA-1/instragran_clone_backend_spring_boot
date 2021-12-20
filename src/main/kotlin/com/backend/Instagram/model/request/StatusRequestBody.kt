package com.backend.Instagram.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


data class StatusRequestBody(
    var userId: String? = null,
    var statusId: String? = null,
    var statusImageUrl: String = "",
    var statusText: String = "",
)
