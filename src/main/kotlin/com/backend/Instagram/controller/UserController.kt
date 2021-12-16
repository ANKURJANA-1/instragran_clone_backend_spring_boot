package com.backend.Instagram.controller

import com.backend.Instagram.exceptation.BadRequestException
import com.backend.Instagram.model.entity.AppUser
import com.backend.Instagram.model.request.LoginRequestBody
import com.backend.Instagram.model.request.SignupRequestBody
import com.backend.Instagram.model.response.GenericResponse
import com.backend.Instagram.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("api/v1/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("login")
    fun login(
        @Valid
        @RequestBody
        loginRequestBody: LoginRequestBody
    ): GenericResponse {
        return try {
            userService.login(loginRequestBody)
        } catch (e: Exception) {
            throw BadRequestException(
                msg = e.message.toString(),
            )
        }
    }

    @PostMapping("signup")
    fun signUp(
        @Valid
        @RequestBody
        signupRequestBody: SignupRequestBody
    ): GenericResponse {
        return try {
            userService.signUp(signupRequestBody)
        } catch (e: Exception) {
            throw BadRequestException(
                msg = e.message.toString(),
            )
        }
    }

    @GetMapping("me")
    fun me(
        @RequestParam
        id: String,

        ): GenericResponse {
        return try {
            userService.me(id)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    @PostMapping("updateProfile")
    fun updateProfile(
        @Valid
        @RequestBody
        appUser: AppUser
    ): GenericResponse {
        return try {
            userService.updateProfile(appUser)
        } catch (e: Exception) {
            throw BadRequestException(msg = e.message.toString())
        }
    }

    @PostMapping("changePassword")
    fun changePassword(
        @RequestParam
        id: String,
        @RequestParam
        oldPassword: String,
        @RequestParam
        newPassword: String
    ): GenericResponse {
        return try {
            userService.changePassword(
                id,
                oldPassword,
                newPassword
            )
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }

    @GetMapping("allPost")
    fun allPost(
        @RequestParam
        userId: String
    ): GenericResponse {
        return try {
            userService.getAllPost(userId)
        } catch (e: Exception) {
            throw BadRequestException(e.message.toString())
        }
    }

}