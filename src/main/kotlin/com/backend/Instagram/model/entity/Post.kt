package com.backend.Instagram.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity(name = "posts")
data class Post(
    @Id
    val postId: String = UUID.randomUUID().toString(),
    @JsonIgnore
    @ManyToOne
    var appUser: AppUser = AppUser(),
    var postImagesUrl: String = "",
    var postText: String = "",
    var userId: String = "",
    var postLike: Long = 0L,
    var alreadyLiked: Boolean = false,
    var postActive: Boolean = true,
    @OneToMany(mappedBy = "post")
    var postComment: List<Comment> = ArrayList()
)