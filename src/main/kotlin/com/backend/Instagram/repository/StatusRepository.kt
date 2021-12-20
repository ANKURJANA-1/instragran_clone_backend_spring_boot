package com.backend.Instagram.repository

import com.backend.Instagram.model.entity.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StatusRepository : JpaRepository<Status, String> {

    @Query(
        value = "SELECT * FROM status WHERE user_id=?1 AND status_active=true",
        nativeQuery = true
    )
    fun findByUserIdAndStatusActive(userId: String): List<Status>

    @Query(
        value = "SELECT * FROM status WHERE id=?1 AND status_active=true",
        nativeQuery = true
    )
    fun findByIdAndStatusActive(statusId: String): List<Status>

    @Query(
        value = "SELECT * FROM status WHERE id=?1 AND status_active=true AND already_liked=true",
        nativeQuery = true
    )
    fun findByIdAndAlreadyLikedAndStatusId(statusId: String): List<Status>
}