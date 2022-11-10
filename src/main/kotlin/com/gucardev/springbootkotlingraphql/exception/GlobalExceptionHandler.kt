package com.gucardev.springbootkotlingraphql.exception

import graphql.ErrorType
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.stereotype.Component

@Component
class GlobalExceptionHandler : DataFetcherExceptionResolverAdapter() {


    override fun resolveToSingleError(e: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        return when (e) {
            is UserNotFoundException -> toGraphQLError(e)
            is Exception -> toGraphQLError(e)
            else -> super.resolveToSingleError(e, env)
        }
    }

    private fun toGraphQLError(e: Throwable): GraphQLError? {
        return GraphqlErrorBuilder.newError().message(e.message).errorType(ErrorType.DataFetchingException).build()
    }


}