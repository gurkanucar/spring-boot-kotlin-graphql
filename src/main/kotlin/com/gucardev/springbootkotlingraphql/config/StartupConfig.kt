package com.gucardev.springbootkotlingraphql.config

import com.gucardev.springbootkotlingraphql.model.Role
import com.gucardev.springbootkotlingraphql.request.UserCreateRequest
import com.gucardev.springbootkotlingraphql.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


@Component
class StartupConfig(private val userService: UserService) : CommandLineRunner {


    override fun run(vararg args: String?) {
//        userService.createUser(
//            UserCreateRequest(
//                username = "grkn",
//                email = "mail1",
//                role = Role.ADMIN,
//                name = "gurkan",
//                surname = "ucar"
//            )
//        )
//        userService.createUser(UserCreateRequest(username = "ahmet", email = "mail2", role = Role.USER))
//        userService.createUser(UserCreateRequest(username = "mehmet", email = "mail3", role = Role.USER))
    }
}