package com.backend.Instagram.model.request


data class StatusRequestBody(
    var userId: String? = null,
    var statusId: String? = null,
    var statusImageUrl: String = "",
    var statusText: String = "",
)
