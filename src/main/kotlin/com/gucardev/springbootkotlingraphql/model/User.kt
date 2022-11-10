package com.gucardev.springbootkotlingraphql.model

import javax.persistence.*

@Entity
@Table(name = "`users`")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,
    val username: String,
    val email: String,
    val name: String? = null,
    val surname: String? = null,
    @Enumerated(EnumType.STRING)
    val role: Role,
) : BaseEntity()