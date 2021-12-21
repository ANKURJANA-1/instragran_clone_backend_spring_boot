package com.backend.Instagram.controller

import com.backend.Instagram.exceptation.BadRequestException
import com.backend.Instagram.model.request.SeenStatusRequestBody
import com.backend.Instagram.model.response.GenericResponse
import com.backend.Instagram.service.StatusSeenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/seen")
class SeenController {

    @Autowired
    private lateinit var seenService: StatusSeenService

    @PostMapping()
    fun seen(
        @RequestBody
        seenStatusRequestBody: SeenStatusRequestBody
    ): GenericResponse {
        return try {
            seenService.seen(seenStatusRequestBody)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }
}