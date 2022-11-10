package com.gucardev.springbootkotlingraphql.exception

import org.jetbrains.annotations.NotNull

class UserNotFoundException(@NotNull message: String) : RuntimeException(message) {
}