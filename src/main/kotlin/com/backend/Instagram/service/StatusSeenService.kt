package com.backend.Instagram.service

import com.backend.Instagram.exceptation.BadRequestException
import com.backend.Instagram.model.entity.StatusSeen
import com.backend.Instagram.model.request.SeenStatusRequestBody
import com.backend.Instagram.model.response.GenericResponse
import com.backend.Instagram.repository.StatusRepository
import com.backend.Instagram.repository.StatusSeenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*


@Service
class StatusSeenService {

    @Autowired
    private lateinit var statusRepository: StatusRepository

    @Autowired
    private lateinit var statusSeenRepository: StatusSeenRepository

    
    fun seen(seenStatusRequestBody: SeenStatusRequestBody): GenericResponse {
        try {
            UUID.fromString(seenStatusRequestBody.statusId)
            UUID.fromString(seenStatusRequestBody.userId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid status id && user id")
        }
        val foundStatusById = statusRepository.findById(seenStatusRequestBody.statusId)

        if (!foundStatusById.isPresent) {
            throw BadRequestException(msg = "status not found")
        }

        if (!foundStatusById.get().statusActive) {
            throw BadRequestException(msg = "Enter correct active status id")
        }
        val foundByStatusID = statusSeenRepository.findByStatusIdAndUserId(
            seenStatusRequestBody.statusId, seenStatusRequestBody.userId
        )
        if (foundByStatusID.isEmpty()) {
            throw BadRequestException(msg = "Please add status")
        }
        if (foundByStatusID[0].alreadySeen) {
            return GenericResponse(message = "Already seen")
        }
        val status = foundStatusById.get()
        status.seenCount += 1

        val seen = StatusSeen(
            userId = seenStatusRequestBody.userId,
            statusId = seenStatusRequestBody.statusId,
            alreadySeen = true,
            status = status
        )


        return try {
            statusRepository.save(status)
            statusSeenRepository.save(seen)
            GenericResponse(message = "ok", body = seen)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }
}