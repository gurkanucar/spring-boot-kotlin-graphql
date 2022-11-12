package com.gucardev.springbootkotlingraphql.converter

import com.gucardev.springbootkotlingraphql.dto.UserDTO
import com.gucardev.springbootkotlingraphql.model.Role
import com.gucardev.springbootkotlingraphql.model.User
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


}