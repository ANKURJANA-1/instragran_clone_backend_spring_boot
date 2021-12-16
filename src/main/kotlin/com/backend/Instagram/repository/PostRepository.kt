package com.backend.Instagram.repository

import com.backend.Instagram.model.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, String> {
}