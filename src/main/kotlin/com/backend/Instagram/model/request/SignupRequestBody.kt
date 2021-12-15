package com.backend.Instagram.model.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class SignupRequestBody(
    @field:NotNull(message = "Please provide username")
    @field:NotBlank(message = "Please provide valid username")
    var userName: String = "",
    @field:NotNull(message = "Please provide email id")
    @field:NotBlank(message = "Please provide valid email id")
    @field:Email(
        message = "Email is not valid",
        regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    )
    var emailId: String = "",
    var mobileNumberCountryCode: String = "",
    @field:NotNull(message = "Please provide mobile no.")
    @field:NotBlank(message = "Please provide valid mobile no.")
    var mobileNumber: String = "",
    @field:NotNull(message = "Please provide address")
    @field:NotBlank(message = "Please provide valid address")
    var address: String = "",
    @field:NotNull(message = "Please provide country id")
    @field:NotBlank(message = "Please provide valid country id")
    var country: String = "",
    @field:NotNull(message = "Please provide password")
    @field:NotBlank(message = "Please provide valid password")
    var password: String = "",
)
