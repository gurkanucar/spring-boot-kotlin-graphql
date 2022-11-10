package com.gucardev.springbootkotlingraphql.model

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "`users`")
data class User(
    val username: String,
    val email: String,
    val name: String? = null,
    val surname: String? = null,
    @Enumerated(EnumType.STRING)
    val role: Role,
) : BaseEntity()