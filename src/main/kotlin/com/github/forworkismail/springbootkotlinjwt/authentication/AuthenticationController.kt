package com.github.forworkismail.springbootkotlinjwt.authentication

import com.github.forworkismail.springbootkotlinjwt.authentication.dto.RegisterUserRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("authentication")
class AuthenticationController {

    @PostMapping("register")
    fun register(@RequestBody body: RegisterUserRequest): String {
        return "register"
    }
}