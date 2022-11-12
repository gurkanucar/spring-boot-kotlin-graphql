package com.gucardev.springbootkotlingraphql.dto

import com.gucardev.springbootkotlingraphql.model.Role
import java.time.OffsetDateTime

data class UserDTO(
    var id: Long? = null,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null,
    val username: String,
    val email: String,
    val name: String? = null,
    val surname: String? = null,
    val role: Role
)