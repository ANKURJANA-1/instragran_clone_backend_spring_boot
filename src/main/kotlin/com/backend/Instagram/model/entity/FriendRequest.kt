package com.backend.Instagram.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne


@Entity(name = "friend_requests")
data class FriendRequest(
    @Id
    val friendRequestId: String = UUID.randomUUID().toString(),
    var friendUserId: String = "",
    var accepted: Boolean = false,
    var withdraw: Boolean = false,
    @JsonIgnore
    @ManyToOne
    var appUser: AppUser = AppUser(),
    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    var updateAt: LocalDateTime = LocalDateTime.now()
)
