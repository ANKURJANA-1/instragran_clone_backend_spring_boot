package com.backend.Instagram.repository

import com.backend.Instagram.model.entity.StatusSeen
import com.backend.Instagram.model.request.SeenStatusRequestBody
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StatusSeenRepository : JpaRepository<StatusSeen, String> {

    @Query(value = "SELECT * FROM status_seen WHERE status_id=?1 AND user_id=?2", nativeQuery = true)
    fun findByStatusIdAndUserId(
        statusId: String,
        userId: String
    ): List<StatusSeen>
}