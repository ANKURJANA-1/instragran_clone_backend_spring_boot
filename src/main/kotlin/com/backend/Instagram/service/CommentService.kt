package com.backend.Instagram.service

import com.backend.Instagram.exceptation.BadRequestException
import com.backend.Instagram.model.entity.Comment
import com.backend.Instagram.model.request.CommentRequestBody
import com.backend.Instagram.model.request.EditCommentRequestBody
import com.backend.Instagram.model.response.GenericResponse
import com.backend.Instagram.repository.CommentRepository
import com.backend.Instagram.repository.PostRepository
import com.backend.Instagram.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class CommentService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var postRepository: PostRepository

    @Autowired
    private lateinit var commentRepository: CommentRepository

    fun addComment(
        commentRequestBody: CommentRequestBody
    ): GenericResponse {
        try {
            UUID.fromString(commentRequestBody.postId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user and post id")
        }

        val foundUserPostByPostId = postRepository.findById(commentRequestBody.postId)
        if (!foundUserPostByPostId.isPresent) {
            throw BadRequestException("Post does not exists")
        }

        val comment = Comment(
            postId = commentRequestBody.postId,
            commentUserId = foundUserPostByPostId.get().userId,
            commentText = commentRequestBody.commentText,
            post = foundUserPostByPostId.get()
        )

        return try {
            val saveComment = commentRepository.save(comment)
            GenericResponse("Ok", body = saveComment)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }


    fun deleteComment(commentId: String): GenericResponse {
        try {
            UUID.fromString(commentId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid post id")
        }

        val foundCommentByCommentId = commentRepository.findById(commentId)
        if (!foundCommentByCommentId.isPresent) {
            throw BadRequestException("Comment does not exists")
        }
        if (!foundCommentByCommentId.get().commentActive) {
            throw BadRequestException("Comment does not exists")
        }

        return try {
            foundCommentByCommentId.get().commentActive = false
            commentRepository.save(foundCommentByCommentId.get())
            GenericResponse("Comment Deleted")
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun allComments(postId: String): GenericResponse {
        try {
            UUID.fromString(postId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid post id")
        }

        val postByPostId = postRepository.findById(postId)
        if (!postByPostId.isPresent) {
            throw BadRequestException(msg = "Post does not exists")
        }
        return try {
            val comments = postByPostId.get().postComment
            GenericResponse(message = "Ok", body = comments)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun editComment(editCommentRequestBody: EditCommentRequestBody): GenericResponse {
        try {
            UUID.fromString(editCommentRequestBody.commentId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid comment id")
        }
        val foundCommentByID = commentRepository.findById(editCommentRequestBody.commentId)

        if (!foundCommentByID.isPresent) {
            throw BadRequestException(msg = "Comment does not exists")
        }
        return try {
            val comment = foundCommentByID.get()
            comment.commentText = editCommentRequestBody.commentText
            commentRepository.save(comment)
            GenericResponse(message = "Comment update", body = comment)
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }

    fun likeComment(commentId: String): GenericResponse {
        try {
            UUID.fromString(commentId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid comment id")
        }
        val foundCommentById = commentRepository.findById(commentId)
        return try {
            val comment = foundCommentById.get()
            comment.likeComment += 1
            comment.alreadyLiked = true
            commentRepository.save(comment)
            GenericResponse(message = "Ok")
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }
}