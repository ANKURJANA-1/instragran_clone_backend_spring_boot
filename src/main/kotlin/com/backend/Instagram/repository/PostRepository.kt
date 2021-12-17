package com.backend.Instagram.repository

import com.backend.Instagram.model.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface PostRepository : JpaRepository<Post, String> {

    @Query(
        value = "SELECT * FROM posts WHERE post_id=?1 AND post_active=true",
        nativeQuery = true
    )
    fun findByIdAndPostActive(postId: String): List<Post>
}