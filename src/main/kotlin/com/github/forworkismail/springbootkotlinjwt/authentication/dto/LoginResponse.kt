package com.github.forworkismail.springbootkotlinjwt.authentication.dto

data class LoginResponse (
        val accessToken: String,
        val refreshToken: String
        )