package com.gucardev.springbootkotlingraphql.request

import com.gucardev.springbootkotlingraphql.model.Role

data class UserCreateRequest(
    val username: String,
    val email: String,
    val name: String? = null,
    val surname: String? = null,
    val role: Role
)
