package com.backend.Instagram.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class EditCommentRequestBody(
    @field:NotNull(message = "Please provide postId")
    @field:NotBlank(message = "Please provide valid postId")
    var commentId: String = "",
    @field:NotNull(message = "Please provide commentText")
    @field:NotBlank(message = "Please provide valid commentText")
    var commentText: String = "",
)
