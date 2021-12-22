package com.backend.Instagram.service

import com.backend.Instagram.exceptation.BadRequestException
import com.backend.Instagram.model.entity.Message
import com.backend.Instagram.model.request.GetMessageRequestBody
import com.backend.Instagram.model.request.MessageRequestBody
import com.backend.Instagram.model.response.GenericResponse
import com.backend.Instagram.repository.MessageRepository
import com.backend.Instagram.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageService {

    @Autowired
    private lateinit var messageRepository: MessageRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    fun sendMessage(messageRequestBody: MessageRequestBody): GenericResponse {
        try {
            UUID.fromString(messageRequestBody.userId)
            UUID.fromString(messageRequestBody.recipientId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user id or recipient id")
        }

        val user = userRepository.findById(messageRequestBody.recipientId)
        if (!user.isPresent) {
            throw BadRequestException(msg = "user does not exists")
        }

        if (!user.get().allowForMessage) {
            throw BadRequestException(msg = "you are not permitted to send message")
        }

        val message = Message(
            userId = messageRequestBody.userId,
            messageBody = messageRequestBody.messageBody,
            recipientId = messageRequestBody.recipientId,
            appUser = user.get()
        )

        return try {
            messageRepository.save(message)
            GenericResponse(message = "send", body = message)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun getMessage(getMessageRequestBody: GetMessageRequestBody): GenericResponse {
        try {
            UUID.fromString(getMessageRequestBody.userId)
            UUID.fromString(getMessageRequestBody.recipientId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user id or recipient id")
        }

        val foundMessagesById = messageRepository.findByUserIdAndRecipientId(
            getMessageRequestBody.userId,
            getMessageRequestBody.recipientId
        )

        if (foundMessagesById.isEmpty()) {
            return GenericResponse(message = "Enter message")
        }

        return try {
            GenericResponse(message = "Ok", body = foundMessagesById[0])
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun deleteMessage(getMessageRequestBody: GetMessageRequestBody): GenericResponse {
        try {
            UUID.fromString(getMessageRequestBody.userId)
            UUID.fromString(getMessageRequestBody.recipientId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user id or recipient id")
        }
        val message = messageRepository.findByUserIdAndRecipientId(
            getMessageRequestBody.userId,
            getMessageRequestBody.recipientId
        )

        if (message.isEmpty()) {
            return GenericResponse(message = "message box is empty")
        }
        return try {
            message[0].messageDelete = true
            messageRepository.save(message[0])
            GenericResponse(message = "message is delete successfully")
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun blockUser(getMessageRequestBody: GetMessageRequestBody): GenericResponse {
        try {
            UUID.fromString(getMessageRequestBody.userId)
            UUID.fromString(getMessageRequestBody.recipientId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user id or recipient id")
        }

        val user = userRepository.findById(getMessageRequestBody.recipientId)

        if (!user.isPresent) {
            throw BadRequestException(msg = "user is already block")
        }
        return try {
            user.get().allowForMessage = false
            userRepository.save(user.get())
            GenericResponse(message = "user blocked successfully")
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun allUser(getMessageRequestBody: GetMessageRequestBody) {
        try {
            UUID.fromString(getMessageRequestBody.userId)
            UUID.fromString(getMessageRequestBody.recipientId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user id or recipient id")
        }
    }

}