package com.backend.Instagram.service

import com.backend.Instagram.exceptation.BadRequestException
import com.backend.Instagram.model.entity.Post
import com.backend.Instagram.model.request.PostRequestBody
import com.backend.Instagram.model.response.GenericResponse
import com.backend.Instagram.repository.PostRepository
import com.backend.Instagram.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var postRepository: PostRepository

    fun uploadPost(postRequestBody: PostRequestBody): GenericResponse {
        try {
            UUID.fromString(postRequestBody.userId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user id")
        }

        val findUserById = userRepository.findById(postRequestBody.userId)
        if (!findUserById.isPresent) {
            throw BadRequestException(msg = "User not found")
        }
        val post = Post(
            appUser = findUserById.get(),
            userId = findUserById.get().id
        )
        if (!postRequestBody.postText.isNullOrEmpty()) {
            post.postText = postRequestBody.postText.toString()
        }
        if (!postRequestBody.postImagesUrl.isNullOrEmpty()) {
            post.postImagesUrl = postRequestBody.postImagesUrl.toString()
        }

        return try {
            postRepository.save(post)
            GenericResponse(message = "Your post successfully upload")
        } catch (e: Exception) {
            throw BadRequestException(
                msg = e.message.toString(),
                status = HttpStatus.BAD_REQUEST
            )
        }
    }

    fun addLike(postId: String): GenericResponse {
        try {
            UUID.fromString(postId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user id")
        }
        val foundPostByPostId = postRepository.findByIdAndPostActive(postId)
        if (foundPostByPostId.isEmpty()) {
            throw BadRequestException(msg = "Post not present")
        }
        val post = foundPostByPostId[0]
        if (!post.postActive) {
            throw BadRequestException(msg = "Post not present")
        }
        if (post.alreadyLiked) {
            throw BadRequestException(msg = "Post already liked")
        }
        return try {
            post.postLike += 1
            post.alreadyLiked = true
            postRepository.save(post)
            GenericResponse(
                message = "Ok",
                body = post
            )
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun editPost(postRequestBody: PostRequestBody): GenericResponse {
        try {
            UUID.fromString(postRequestBody.userId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user id")
        }
        val foundPostByPostId = postRepository.findByIdAndPostActive(postRequestBody.userId)
        if (foundPostByPostId.isEmpty()) {
            throw BadRequestException(msg = "Post not present")
        }
        return try {
            val post = foundPostByPostId[0]
            if (postRequestBody.postText.isNullOrEmpty()) {
                post.postText = postRequestBody.postText.toString()
            }
            if (postRequestBody.postImagesUrl.isNullOrEmpty()) {
                post.postImagesUrl = postRequestBody.postImagesUrl.toString()
            }
            GenericResponse(
                message = "Ok",
                body = post
            )
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun deletePost(postId: String): GenericResponse {
        try {
            UUID.fromString(postId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user id")
        }
        val foundPostByPostId = postRepository.findByIdAndPostActive(postId)
        if (foundPostByPostId.isEmpty()) {
            throw BadRequestException(msg = "Post not present")
        }

        return try {
            val post = foundPostByPostId[0]
            post.postActive = false
            postRepository.save(post)
            GenericResponse(message = "Post delete successfully")
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

}