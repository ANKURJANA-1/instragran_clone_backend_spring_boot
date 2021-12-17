package com.backend.Instagram.controller

import com.backend.Instagram.exceptation.BadRequestException
import com.backend.Instagram.model.request.CommentRequestBody
import com.backend.Instagram.model.request.EditCommentRequestBody
import com.backend.Instagram.model.response.GenericResponse
import com.backend.Instagram.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1/comment")
class CommentController {

    @Autowired
    private lateinit var commentService: CommentService

    @GetMapping("allComments")
    fun allComments(
        @RequestParam
        postId: String
    ): GenericResponse {
        return try {
            commentService.allComments(postId)
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }

    @PostMapping("addComment")
    fun addComment(
        @RequestBody
        commentRequestBody: CommentRequestBody
    ): GenericResponse {
        return try {
            commentService.addComment(commentRequestBody)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    @PostMapping("deleteComment")
    fun deleteComment(
        @RequestParam
        commentId: String
    ): GenericResponse {
        return try {
            commentService.deleteComment(commentId)
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }

    @PostMapping("editComment")
    fun editComment(
        @RequestBody
        editCommentRequestBody: EditCommentRequestBody
    ): GenericResponse {
        return try {
            commentService.editComment(editCommentRequestBody)
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }

    @PostMapping("likeComment")
    fun likeComment(
        @RequestParam
        commentId: String
    ): GenericResponse {
        return try {
            commentService.likeComment(commentId)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }
}