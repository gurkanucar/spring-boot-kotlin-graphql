package com.gucardev.springbootkotlingraphql.converter

import com.gucardev.springbootkotlingraphql.dto.UserDTO
import com.gucardev.springbootkotlingraphql.model.User
import com.gucardev.springbootkotlingraphql.request.UserCreateRequest

fun User.toDTO(): UserDTO {
    return UserDTO(
        id = this.id,
        username = this.username,
        email = this.email,
        name = this.name,
        surname = this.surname,
    )
}


fun UserCreateRequest.toUSER(): User {
    return User(
        username = this.username,
        email = this.email,
        name = this.name,
        surname = this.surname,
    )
}
