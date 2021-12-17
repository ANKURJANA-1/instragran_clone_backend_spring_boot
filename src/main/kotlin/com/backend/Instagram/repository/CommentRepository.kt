package com.backend.Instagram.repository

import com.backend.Instagram.model.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, String> {
}