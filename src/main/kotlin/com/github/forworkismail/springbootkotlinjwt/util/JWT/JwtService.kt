package com.github.forworkismail.springbootkotlinjwt.util.JWT

import io.jsonwebtoken.Claims
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

interface JwtService {
    fun getUsernameFromToken(token: String): String?
    fun getExpirationDateFromToken(token: String): Date?
    fun getClaimFromToken(token: String, claimName: String): Any?
    fun getAllClaimsFromToken(token: String): Claims
    fun isTokenExpired(token: String): Boolean
    fun generateToken(userDetails: UserDetails): String
    fun doGenerateToken(claims: Map<String, Any>, subject: String): String
    fun validateToken(token: String, userDetails: UserDetails): Boolean

    fun generateRefreshToken(userDetails: UserDetails): String
}