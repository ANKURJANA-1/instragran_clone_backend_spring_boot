package com.backend.Instagram.service

import com.backend.Instagram.exceptation.BadRequestException
import com.backend.Instagram.model.response.GenericResponse
import com.backend.Instagram.repository.FriendRequestBody
import com.backend.Instagram.repository.FriendRequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class FriendRequestService {

    @Autowired
    private lateinit var friendRequestRepository: FriendRequestRepository

    fun acceptRequest(friendRequestId: String): GenericResponse {
        try {
            UUID.fromString(friendRequestId)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
        val request = friendRequestRepository.findById(friendRequestId)

        if (!request.isPresent) {
            throw BadRequestException(msg = "request not available")
        }

        val requestBody = request.get()
        if (requestBody.accepted) {
            throw BadRequestException(msg = "request already accepted")
        }

        return try {
            requestBody.accepted = true
            friendRequestRepository.save(requestBody)
            GenericResponse(message = "request accepted")
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }


    fun withdrawRequest(friendRequestId: String): GenericResponse {
        try {
            UUID.fromString(friendRequestId)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
        val request = friendRequestRepository.findById(friendRequestId)

        if (!request.isPresent) {
            throw BadRequestException(msg = "request not available")
        }
        val requestBody = request.get()
        if (!requestBody.withdraw) {
            throw BadRequestException(msg = "request already accepted")
        }

        return try {
            requestBody.withdraw = true
            friendRequestRepository.save(requestBody)
            GenericResponse(message = "request withdraw successfully")
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

}