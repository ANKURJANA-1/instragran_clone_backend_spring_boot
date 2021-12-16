package com.backend.Instagram.model.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "posts")
data class Post(
    @Id
    val postId: String = UUID.randomUUID().toString(),
    @ManyToOne
    var appUser: AppUser = AppUser(),
    @Column(name = "post_image")
    var postImagesUrl: String = "",
    @Column(name = "post_text")
    var postText: String = "",
    @Column(name = "post_like")
    var postLike: Long = 0L,
//    @Column(name = "post_comments")
//    var postComment: String = ""
)