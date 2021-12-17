package com.backend.Instagram.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CommentRequestBody(
    @field:NotNull(message = "Please provide postId")
    @field:NotBlank(message = "Please provide valid postId")
    var postId: String = "",
    @field:NotNull(message = "Please provide commentText")
    @field:NotBlank(message = "Please provide valid commentText")
    var commentText: String = "",
)
