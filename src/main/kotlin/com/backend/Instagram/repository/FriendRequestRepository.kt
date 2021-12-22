package com.backend.Instagram.repository

import com.backend.Instagram.model.entity.FriendRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface FriendRequestRepository : JpaRepository<FriendRequest, String> {
}