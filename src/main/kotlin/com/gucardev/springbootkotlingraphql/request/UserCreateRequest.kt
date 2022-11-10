package com.gucardev.springbootkotlingraphql.request

data class UserCreateRequest(
    val username: String,
    val email: String,
    val name: String? = null,
    val surname: String? = null
)
