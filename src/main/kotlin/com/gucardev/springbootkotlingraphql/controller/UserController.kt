package com.gucardev.springbootkotlingraphql.controller

import com.gucardev.springbootkotlingraphql.converter.toDTO
import com.gucardev.springbootkotlingraphql.request.UserCreateRequest
import com.gucardev.springbootkotlingraphql.request.UserUpdateRequest
import com.gucardev.springbootkotlingraphql.service.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class UserController(private val userService: UserService) {

    @QueryMapping
    fun getAllUsers() = userService.getAllUsers().map { it.toDTO() }

    @QueryMapping
    fun getByID(@Argument id: Long) = userService.getUserByID(id).toDTO()

    @MutationMapping
    fun updateUser(@Argument user: UserUpdateRequest) = userService.updateUser(user).toDTO()

    @MutationMapping
    fun createUser(@Argument user: UserCreateRequest) = userService.createUser(user).toDTO()

    @MutationMapping
    fun deleteUser(@Argument id: Long) = userService.deleteUser(id)
}