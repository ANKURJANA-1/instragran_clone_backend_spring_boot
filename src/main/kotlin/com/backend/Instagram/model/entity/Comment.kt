package com.backend.Instagram.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne


@Entity(name = "comments")
data class Comment(
    @Id
    var commentID: String = UUID.randomUUID().toString(),
    var postId: String = "",
    var commentUserId: String = "",
    var commentText: String = "",
    var likeComment: Long = 0L,
    var alreadyLiked: Boolean = false,
    var commentActive: Boolean = true,
    @JsonIgnore
    @ManyToOne
    var post: Post = Post(),

    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    var updateAt: LocalDateTime = LocalDateTime.now()
)
