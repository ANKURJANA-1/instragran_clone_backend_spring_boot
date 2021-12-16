package com.backend.Instagram.model.request

import javax.persistence.Column
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PostRequestBody(
    @field:NotNull(message = "Please add userId")
    @field:NotBlank(message = "userId field should be filled")
    var userId: String = "",

    @field:NotNull(message = "Please add image")
    @field:NotBlank(message = "Image field should be filled")
    var postImagesUrl: String? = null,

    var postText: String? = null,
)
