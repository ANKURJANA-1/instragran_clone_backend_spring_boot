package com.backend.Instagram.repository

import com.backend.Instagram.model.entity.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface MessageRepository : JpaRepository<Message, String> {

    @Query(
        "SELECT * FROM messages WHERE user_id=?1 AND recipient_id=?2 AND message_delete=false",
        nativeQuery = true
    )
    fun findByUserIdAndRecipientId(
        userId: String,
        recipientId: String
    ): List<Message>

}