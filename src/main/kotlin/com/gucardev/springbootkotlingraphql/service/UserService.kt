package com.gucardev.springbootkotlingraphql.service

import com.gucardev.springbootkotlingraphql.converter.toUSER
import com.gucardev.springbootkotlingraphql.converter.toUser
import com.gucardev.springbootkotlingraphql.exception.UserNotFoundException
import com.gucardev.springbootkotlingraphql.model.User
import com.gucardev.springbootkotlingraphql.repository.UserRepository
import com.gucardev.springbootkotlingraphql.request.UserCreateRequest
import com.gucardev.springbootkotlingraphql.request.UserUpdateRequest
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun createUser(user: UserCreateRequest): User = userRepository.save(user.toUSER())

    fun getUserByID(id: Long): User = userRepository.findById(id)
        .orElseThrow { UserNotFoundException("user not found!") }

    fun deleteUser(id: Long) {
        val existing = getUserByID(id)
        userRepository.delete(existing)
    }

    fun updateUser(updateUserRequest: UserUpdateRequest): User {
        val existing = getUserByID(updateUserRequest.id)
        return  userRepository.save(updateUserRequest.toUser(existing))
    }

}