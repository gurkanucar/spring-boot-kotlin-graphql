package com.gucardev.springbootkotlingraphql.controller

import com.gucardev.springbootkotlingraphql.dto.UserDTO
import com.gucardev.springbootkotlingraphql.model.Role
import com.gucardev.springbootkotlingraphql.request.UserCreateRequest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.graphql.test.tester.GraphQlTester
import org.springframework.test.annotation.DirtiesContext


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureGraphQlTester
internal class UserControllerTest @Autowired constructor(
    private val graphQlTester: GraphQlTester
) {

    @BeforeEach
    fun setUp() {
        createUser(
            UserCreateRequest(username = "@grkn", email = "mail1", name = "Gurkan", surname = "UCAR", role = Role.ADMIN)
        )
        createUser(
            UserCreateRequest(
                username = "@mehmet", email = "mail2", name = "Mehmet", surname = "YILMAZ", role = Role.USER
            )
        )
        println("BEFORE EACH!")
    }

    @Test
    fun `should return all users`() {
        val query: String = """
       query{
          getAllUsers {
            id
            username
            email
            role
            name
            surname
            createdAt
            updatedAt
          }
        }
     """

        graphQlTester.document(query)
            .execute()
            .path("getAllUsers")
            .entityList(UserDTO::class.java)
            .hasSize(2)


    }

    @Test
    fun `should create new user`() {

        val query: String = """
       mutation {
          createUser(
            user: {username: "grkn2", email: "mail_CREATED", role: ADMIN, name: "Gurkan_CREATED", surname: "Ucar_CREATED"}
          ) {
            id
            username
            email
            name
            surname
            createdAt
            role
          }
        }
     """

        graphQlTester.document(query)
            .execute()
            .path("createUser")
            .entity(UserDTO::class.java)


    }

    private fun createUser(user: UserCreateRequest) {
        val query: String = """
            mutation {
              createUser(
                user: {username: "${user.username}", email: "${user.email}", role: ${user.role}, name: "${user.name}", surname: "${user.surname}"}
              ) {
                id
                email
                username
                createdAt
                role
              }
            }
      """
        graphQlTester.document(query)
            .execute()
            .path("createUser")
            .entity(UserDTO::class.java)
    }

}

