package com.gucardev.springbootkotlingraphql.converter

import com.gucardev.springbootkotlingraphql.dto.UserDTO
import com.gucardev.springbootkotlingraphql.model.Role
import com.gucardev.springbootkotlingraphql.model.User
import com.gucardev.springbootkotlingraphql.request.UserCreateRequest
import com.gucardev.springbootkotlingraphql.request.UserUpdateRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UserConverterKtTest {


    @Test
    fun `User to DTO`() {

        val user = User(
            id = 1L,
            username = "grkn",
            email = "mail1",
            role = Role.USER,
            name = "gurkan",
            surname = "ucar"
        )

        val expected = UserDTO(
            id = 1L,
            username = "grkn",
            email = "mail1",
            role = Role.USER,
            name = "gurkan",
            surname = "ucar"
        )

        val actual = user.toDTO()
        assertEquals(expected, actual)

    }

    @Test
    fun `UserCreateRequest to User`() {
        val userCreateRequest = UserCreateRequest(
            username = "grkn",
            email = "mail1",
            role = Role.USER,
            name = "gurkan",
            surname = "ucar"
        )
        val expected = User(
            id = null,
            username = "grkn",
            email = "mail1",
            role = Role.USER,
            name = "gurkan",
            surname = "ucar"
        )
        val actual = userCreateRequest.toUSER()
        assertEquals(expected, actual)

    }

    @Test
    fun `UserUpdateRequest to User`() {
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
        val actual = userUpdateRequest.toUser(existing)
        assertEquals(expected, actual)
    }

}