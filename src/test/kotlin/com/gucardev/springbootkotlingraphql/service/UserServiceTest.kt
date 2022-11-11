package com.gucardev.springbootkotlingraphql.service

import com.gucardev.springbootkotlingraphql.exception.UserNotFoundException
import com.gucardev.springbootkotlingraphql.model.Role
import com.gucardev.springbootkotlingraphql.model.User
import com.gucardev.springbootkotlingraphql.repository.UserRepository
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

}