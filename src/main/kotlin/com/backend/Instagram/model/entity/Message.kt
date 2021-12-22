package com.backend.Instagram.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList


@Entity
@Table(name = "messages")
data class Message(
    @Id
    val messageId: String = UUID.randomUUID().toString(),
    var userId: String = "",
    var messageBody: String = "",
    var recipientId: String = "",
    @JsonIgnore
    @ManyToOne
    var appUser: AppUser = AppUser(),
    var messageDelete: Boolean = false,
    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    var updateAt: LocalDateTime = LocalDateTime.now()
)
