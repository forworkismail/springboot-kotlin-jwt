package com.github.forworkismail.springbootkotlinjwt.authentication

import com.github.forworkismail.springbootkotlinjwt.authentication.dto.LoginRequest
import com.github.forworkismail.springbootkotlinjwt.authentication.dto.LoginResponse
import com.github.forworkismail.springbootkotlinjwt.util.JWT.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("authentication")
class AuthenticationController(private val jwtService: JwtService,
                               private val authenticationManager: AuthenticationManager,
                               private val userDetailsService: UserDetailsService
                                ) {

    @PostMapping("login")
    fun register(@RequestBody body: LoginRequest): LoginResponse {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    body.username,
                    body.password
                )
            )
        } catch (e: BadCredentialsException) {
            throw Exception("Username or password incorrect", e)
        }

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(body.username)

        val accessToken: String = jwtService.generateToken(userDetails)
        val refreshToken: String = jwtService.generateRefreshToken(userDetails)

        return LoginResponse(accessToken, refreshToken)
    }
}