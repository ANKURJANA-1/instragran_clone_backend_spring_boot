package com.backend.Instagram.model.request

data class StatusCommentRequestBody(
    var statusId: String = "",
    var statusText: String = ""
)
