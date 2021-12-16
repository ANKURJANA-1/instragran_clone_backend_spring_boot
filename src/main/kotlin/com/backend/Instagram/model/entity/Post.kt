package com.backend.Instagram.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity(name = "posts")
data class Post(
    @Id
    val postId: String = UUID.randomUUID().toString(),
    @JsonIgnore
    @ManyToOne
    var appUser: AppUser = AppUser(),
    var postImagesUrl: String = "",
    var postText: String = "",
    var postLike: Long = 0L,
    var alreadyLiked: Boolean = false
//    @Column(name = "post_comments")
//    var postComment: String = ""
)