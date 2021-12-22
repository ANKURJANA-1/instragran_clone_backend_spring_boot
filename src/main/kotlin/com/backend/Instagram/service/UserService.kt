package com.backend.Instagram.service

import com.backend.Instagram.exceptation.BadRequestException
import com.backend.Instagram.model.entity.AppUser
import com.backend.Instagram.model.entity.FriendRequest
import com.backend.Instagram.model.request.LoginRequestBody
import com.backend.Instagram.model.request.SignupRequestBody
import com.backend.Instagram.model.response.GenericResponse
import com.backend.Instagram.repository.FriendRequestBody
import com.backend.Instagram.repository.FriendRequestRepository
import com.backend.Instagram.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.batch.BatchDataSource
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var friendRequestRepository: FriendRequestRepository


    fun signUp(signupRequestBody: SignupRequestBody): GenericResponse {
        val foundUser = userRepository.findByUserEmail(signupRequestBody.emailId)
        if (foundUser.isNotEmpty()) {
            throw BadRequestException("User already exists")
        }

        val foundUserByUserName = userRepository.findByUserName(signupRequestBody.userName)
        if (foundUserByUserName.isNotEmpty()) {
            throw BadRequestException("Username already exists")
        }

        return try {
            userRepository.save(
                AppUser(
                    userName = signupRequestBody.userName,
                    userEmail = signupRequestBody.emailId,
                    place = signupRequestBody.address,
                    country = signupRequestBody.country,
                    mobileNo = signupRequestBody.mobileNumber,
                    userPassword = signupRequestBody.password,
                    countryCode = signupRequestBody.mobileNumberCountryCode,
                    active = true,
                    profileComplete = false
                )
            )
            GenericResponse("${signupRequestBody.userName}is created")
        } catch (e: Exception) {
            throw BadRequestException(
                msg = e.message.toString(),
                status = HttpStatus.BAD_REQUEST
            )
        }
    }

    fun login(loginRequestBody: LoginRequestBody): GenericResponse {
        val foundUser = userRepository.findByUserEmail(loginRequestBody.emailId)
        if (foundUser.isEmpty()) {
            throw BadRequestException("User does not exists")
        }
        if (foundUser[0].userPassword != loginRequestBody.password) {
            throw BadRequestException("Please enter a correct password")
        }
        return GenericResponse(
            message = "you successfully login",
            body = foundUser[0]
        )
    }

    fun me(id: String): GenericResponse {
        try {
            UUID.fromString(id)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid id")
        }
        val foundById = userRepository.findById(id)
        if (!foundById.isPresent) {
            throw BadRequestException("User does not exists")
        }

        return try {
            val user = foundById.get()
            GenericResponse("ok", body = user)
        } catch (e: Exception) {
            throw BadRequestException(
                msg = e.message.toString(),
                status = HttpStatus.BAD_REQUEST
            )
        }
    }

    fun updateProfile(
        appUser: AppUser
    ): GenericResponse {
        try {
            UUID.fromString(appUser.id)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user")
        }
        return try {
            userRepository.save(appUser)
            GenericResponse("Update user done")
        } catch (e: Exception) {
            throw BadRequestException(
                msg = e.message.toString(),
                status = HttpStatus.BAD_REQUEST
            )
        }
    }

    fun changePassword(
        id: String,
        oldPassword: String,
        newPassword: String
    ): GenericResponse {
        try {
            UUID.fromString(id)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user")
        }
        val foundById = userRepository.findById(id)
        if (!foundById.isPresent) {
            throw BadRequestException("User does not exists")
        }

        if (foundById.get().userPassword != oldPassword) {
            throw BadRequestException("Enter correct old password")
        }
        if (newPassword == oldPassword) {
            throw BadRequestException("Please enter a new password")
        }

        return try {
            val user = foundById.get()
            user.userPassword = newPassword
            userRepository.save(user)
            GenericResponse("Password changed successful")
        } catch (e: Exception) {
            throw BadRequestException(
                msg = e.message.toString(),
                status = HttpStatus.BAD_REQUEST
            )
        }
    }

    fun forgotPassword(email: String): GenericResponse {
        val foundUser = userRepository.findByUserEmail(email)
        if (foundUser.isEmpty()) {
            throw BadRequestException("User does not exists")
        }
        return GenericResponse("")
    }

    fun getAllPost(userId: String): GenericResponse {
        try {
            UUID.fromString(userId)
        } catch (ex: Exception) {
            throw BadRequestException("Please provide valid user id")
        }
        val foundUserById = userRepository.findById(userId)
        if (!foundUserById.isPresent) {
            throw BadRequestException(msg = "User does not exists")
        }
        return try {
            GenericResponse(
                "Ok",
                body = foundUserById.get().post
            )
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    fun sendFriendRequest(friendRequestBody: FriendRequestBody): GenericResponse {
        try {
            UUID.fromString(friendRequestBody.friendUserId)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
        val foundUser = userRepository.findById(friendRequestBody.friendUserId)
        if (!foundUser.isPresent) {
            throw BadRequestException(msg = "user does not exists")
        }
        val user = foundUser.get()

        if (user.alreadyRequest) {
            throw BadRequestException(msg = "friend request already sent")
        }
        val friendRequest = FriendRequest(
            friendRequestId = friendRequestBody.friendUserId,
            appUser = user
        )
        return try {
            friendRequestRepository.save(friendRequest)
            GenericResponse(
                message = "request sent",
                body = friendRequest
            )
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

}