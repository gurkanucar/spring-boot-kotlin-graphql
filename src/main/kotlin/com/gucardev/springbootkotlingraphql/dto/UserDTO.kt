package com.gucardev.springbootkotlingraphql.dto

import com.gucardev.springbootkotlingraphql.model.Role
import java.time.LocalDateTime

data class UserDTO(
    var id: Long? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val username: String,
    val email: String,
    val name: String? = null,
    val surname: String? = null,
    val role: Role
)