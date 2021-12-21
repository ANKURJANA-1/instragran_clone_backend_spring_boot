package com.backend.Instagram.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList


@Entity(name = "status")
data class Status(
    @Id
    var statusId: String = UUID.randomUUID().toString(),
    @JsonIgnore
    @ManyToOne
    var appUser: AppUser = AppUser(),
    var statusImageUrl: String = "",
    var statusText: String = "",
    var userId: String = "",
    @JsonIgnore
    @OneToMany
    var seen: List<StatusSeen> = ArrayList(),
    var seenCount: Int = 0,
    var statusLike: Long = 0L,
    var alreadyLiked: Boolean = false,
    var statusActive: Boolean = true,
    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    var updateAt: LocalDateTime = LocalDateTime.now()
)
