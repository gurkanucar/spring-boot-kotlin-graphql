package com.gucardev.springbootkotlingraphql.service

import com.gucardev.springbootkotlingraphql.converter.toUSER
import com.gucardev.springbootkotlingraphql.converter.toUser
import com.gucardev.springbootkotlingraphql.exception.UserNotFoundException
import com.gucardev.springbootkotlingraphql.model.Role
import com.gucardev.springbootkotlingraphql.model.User
import com.gucardev.springbootkotlingraphql.repository.UserRepository
import com.gucardev.springbootkotlingraphql.request.UserCreateRequest
import com.gucardev.springbootkotlingraphql.request.UserUpdateRequest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import java.util.*

internal class UserServiceTest() {

    private val userRepository: UserRepository = mockk()
    private val userService: UserService = UserService(userRepository)


    @Test
    fun `should return users`() {
        //given
        val user1 = User(id = 1L, username = "gurkan", email = "mail1", role = Role.ADMIN)
        val user2 = User(id = 2L, username = "mehmet", email = "mail2", role = Role.USER)
        val expected = listOf(user1, user2)
        //when
        every { userRepository.findAll() }.returns(expected)
        val actual = userService.getAllUsers()

        //then
        verify(exactly = 1) { userRepository.findAll() }
        assertEquals(expected, actual)
        assertThat(expected.size).isEqualTo(actual.size)
    }

    @Test
    fun `should return user by id if user exists`() {
        val expected = User(id = 1L, username = "gurkan", email = "mail1", role = Role.ADMIN)
        every { userRepository.findById(expected.id!!) }.returns(Optional.of(expected))
        val actual = userService.getUserByID(expected.id!!)
        verify(exactly = 1) { userRepository.findById(expected.id!!) }
        assertEquals(expected, actual)

    }

    @Test
    fun `should throw exception when user does not exist by given id`() {
        every { userRepository.findById(anyLong()) }.returns(Optional.empty())
        var exceptionThrown: Boolean = false
        try {
            userService.getUserByID(anyLong())
        } catch (e: UserNotFoundException) {
            exceptionThrown = true
        }

        verify(exactly = 1) { userRepository.findById(anyLong()) }
        assertTrue(exceptionThrown)

    }

    @Test
    fun `should create user and return it`() {
        val userCreateRequest = UserCreateRequest(username = "gurkan", email = "mail1", role = Role.ADMIN)
        val expected = User(id = 1L, username = "gurkan", email = "mail1", role = Role.ADMIN)
        every { userRepository.save(userCreateRequest.toUSER()) }.returns(expected)
        val actual = userService.createUser(userCreateRequest)
        verify(exactly = 1) { userRepository.save(userCreateRequest.toUSER()) }
        assertEquals(expected, actual)
    }


    @Test
    fun `should update user and return it`() {
        val userUpdateRequest = UserUpdateRequest(
            id = 1L, username = "grkn_UPDATE",
            email = "mail1_UPDATE",
            role = Role.USER,
            name = "gurkan_UPDATE",
            surname = "ucar_UPDATE"
        )

        val existing = User(
            id = 1L, username = "grkn",
            email = "mail1",
            role = Role.ADMIN,
            name = "gurkan",
            surname = "ucar"
        )

        val expected = User(
            id = 1L, username = "grkn_UPDATE",
            email = "mail1_UPDATE",
            role = Role.USER,
            name = "gurkan_UPDATE",
            surname = "ucar_UPDATE"
        )

        every { userRepository.findById(userUpdateRequest.id) }.returns(Optional.of(existing))
        every { userRepository.save(any()) }.returns(expected)

        val actual = userService.updateUser(userUpdateRequest)
        verify(exactly = 1) { userRepository.findById(userUpdateRequest.id) }
        verify(exactly = 1) { userRepository.save(userUpdateRequest.toUser(existing)) }
        assertEquals(expected, actual)
    }


}