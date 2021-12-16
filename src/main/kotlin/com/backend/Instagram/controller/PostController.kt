package com.backend.Instagram.controller

import com.backend.Instagram.exceptation.BadRequestException
import com.backend.Instagram.model.request.PostRequestBody
import com.backend.Instagram.model.response.GenericResponse
import com.backend.Instagram.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1/post")
class PostController {

    @Autowired
    private lateinit var postService: PostService

    @PostMapping("upload")
    fun uploadPost(
        @RequestBody
        postRequestBody: PostRequestBody
    ): GenericResponse {
        return try {
            postService.uploadPost(postRequestBody)
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }

    @PostMapping("like")
    fun addLike(
        @RequestParam
        postId: String
    ): GenericResponse {
        return try {
            postService.addLike(postId)
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }

    @PostMapping("edit")
    fun editPost(
        @RequestBody
        postRequestBody: PostRequestBody
    ): GenericResponse {
        return try {
            postService.editPost(postRequestBody)
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }

    @PostMapping("delete")
    fun deletePost(
        @RequestParam
        postId: String
    ): GenericResponse {
        return try {
            postService.deletePost(postId)
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }
}