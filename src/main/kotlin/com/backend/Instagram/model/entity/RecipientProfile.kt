package com.backend.Instagram.model.entity

import java.util.*

data class RecipientProfile(
    val recipientId: String = UUID.randomUUID().toString(),
    var recipientFirstName: String = "",
    var recipientLastName: String = "",
    var recipientActive: Boolean = true
)
