package com.backend.Instagram.controller

import com.backend.Instagram.exceptation.BadRequestException
import com.backend.Instagram.model.request.StatusRequestBody
import com.backend.Instagram.model.response.GenericResponse
import com.backend.Instagram.service.StatusService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1/status")
class StatusController {

    @Autowired
    private lateinit var statusService: StatusService

    @PostMapping("addStatus")
    fun addStatus(
        @RequestBody
        statusRequestBody: StatusRequestBody
    ): GenericResponse {
        return try {
            statusService.addStatus(statusRequestBody)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    @GetMapping("currentAllStatus")
    fun currentAllStatus(
        @RequestParam
        userId: String
    ): GenericResponse {
        return try {
            statusService.currentAllStatus(userId)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    @DeleteMapping("deleteStatus")
    fun deleteStatus(
        @RequestParam
        statusId: String
    ): GenericResponse {
        return try {
            statusService.deleteStatus(statusId)
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }


    @PostMapping("likeStatus")
    fun likeStatus(
        @RequestParam
        statusId: String
    ): GenericResponse {
        return try {
            statusService.likeStatus(statusId)
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }

    @PutMapping("updateStatus")
    fun updateStatus(
        @RequestBody
        statusRequestBody: StatusRequestBody
    ): GenericResponse {
        return try {
            statusService.updateStatus(statusRequestBody)
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }
}