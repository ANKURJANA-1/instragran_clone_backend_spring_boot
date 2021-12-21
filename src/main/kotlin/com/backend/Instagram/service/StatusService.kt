package com.backend.Instagram.service

import com.backend.Instagram.exceptation.BadRequestException
import com.backend.Instagram.model.entity.Status
import com.backend.Instagram.model.request.StatusRequestBody
import com.backend.Instagram.model.response.GenericResponse
import com.backend.Instagram.repository.StatusRepository
import com.backend.Instagram.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class StatusService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var statusRepository: StatusRepository

    fun addStatus(statusRequestBody: StatusRequestBody): GenericResponse {
        try {
            UUID.fromString(statusRequestBody.userId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user id")
        }
        val foundUserById = userRepository.findById(statusRequestBody.userId!!)

        if (!foundUserById.isPresent) {
            throw BadRequestException("User does not exists")
        }
        val status = Status(
            appUser = foundUserById.get(),
            statusImageUrl = statusRequestBody.statusImageUrl,
            statusText = statusRequestBody.statusText,
            userId = foundUserById.get().id
        )

        return try {
            statusRepository.save(status)
            GenericResponse(message = "Status saved")
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun currentAllStatus(userId: String): GenericResponse {
        try {
            UUID.fromString(userId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user id")
        }

        val foundAllStatus = statusRepository.findByUserIdAndStatusActive(userId)

        if (foundAllStatus.isEmpty()) {
            throw BadRequestException(msg = " please add status")
        }

        return try {
            GenericResponse(message = "Ok", body = foundAllStatus)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun deleteStatus(statusId: String): GenericResponse {
        try {
            UUID.fromString(statusId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid status id")
        }
        val foundStatusById = statusRepository.findByIdAndStatusActive(statusId)
        if (foundStatusById.isEmpty()) {
            throw BadRequestException(msg = "Status not found")
        }
        return try {
            val status = foundStatusById[0]
            status.statusActive = false
            statusRepository.save(status)
            GenericResponse(message = "delete successfully")
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun likeStatus(statusId: String): GenericResponse {
        try {
            UUID.fromString(statusId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid status id")
        }
        val foundStatusById = statusRepository.findByIdAndAlreadyLikedAndStatusId(statusId)

        if (foundStatusById.isEmpty()) {
            throw BadRequestException(msg = "Status does not exits")
        }

        return try {
            val status = foundStatusById[0]
            status.statusLike += 1
            status.alreadyLiked = true
            statusRepository.save(status)

            GenericResponse(message = "like added", body = status)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun updateStatus(statusRequestBody: StatusRequestBody): GenericResponse {
        try {
            UUID.fromString(statusRequestBody.statusId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid status id")
        }

        val foundById = statusRepository.findById(statusRequestBody.statusId!!)
        if (!foundById.isPresent) {
            throw BadRequestException(msg = "Status does not exists")
        }
        val status = foundById.get()
        if (!statusRequestBody.statusText.isNullOrEmpty()) {
            status.statusText = statusRequestBody.statusText
        }
        if (!statusRequestBody.statusImageUrl.isNullOrEmpty()) {
            status.statusImageUrl = statusRequestBody.statusImageUrl
        }

        return try {
            val updateStatus = statusRepository.save(status)
            GenericResponse(message = "Status update", body = updateStatus)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun commentStatus() {

    }
}