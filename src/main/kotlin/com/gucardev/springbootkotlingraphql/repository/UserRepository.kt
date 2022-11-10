package com.gucardev.springbootkotlingraphql.repository

import com.gucardev.springbootkotlingraphql.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

}