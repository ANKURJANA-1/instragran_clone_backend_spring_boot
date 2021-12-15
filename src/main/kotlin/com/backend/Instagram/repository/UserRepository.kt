package com.backend.Instagram.repository

import com.backend.Instagram.model.entity.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<AppUser, String> {

    fun findByUserEmail(email: String): List<AppUser>
    fun findByUserName(userName: String): List<AppUser>
}