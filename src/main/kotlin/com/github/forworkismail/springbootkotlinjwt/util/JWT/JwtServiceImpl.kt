package com.github.forworkismail.springbootkotlinjwt.util.JWT

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtServiceImpl: JwtService {

    companion object {
        const val JWT_TOKEN_VALIDITY = 60 * 60 * 10
        const val JWT_REFRESH_TOKEN_VALIDITY = 60 * 60 * 24 * 7
    }

    @Value("\${jwt.secret}")
    private val secret: String = ""


    override fun getUsernameFromToken(token: String): String? {
        return getAllClaimsFromToken(token).subject
    }

    override fun getExpirationDateFromToken(token: String): Date? {
        return getAllClaimsFromToken(token).expiration
    }

    override fun getClaimFromToken(token: String, claimName: String): Any? {
        return getAllClaimsFromToken(token)[claimName]
    }

    override fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(secret.toByteArray()).build().parseClaimsJws(token).body
    }

    override fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration!!.before(Date())
    }

    override fun generateToken(userDetails: UserDetails): String {
        // add claims from inside userDetails to object
        val claims = HashMap<String, Any>()
        claims["roles"] = userDetails.authorities.map { it.authority }
        return doGenerateToken(claims, userDetails.username)
    }

    override fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
        val createdDate = Date()
        val expirationDate = Date(createdDate.time + JWT_TOKEN_VALIDITY * 1000)
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
            .setExpiration(expirationDate).signWith(Keys.hmacShaKeyFor(secret.toByteArray()), SignatureAlgorithm.HS512).compact()
    }

    override fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = getUsernameFromToken(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    override fun generateRefreshToken(userDetails: UserDetails): String {
        val createdDate = Date()
        val expirationDate = Date(createdDate.time + JWT_REFRESH_TOKEN_VALIDITY * 1000)
        return Jwts.builder().setSubject(userDetails.username).setIssuedAt(createdDate)
            .setExpiration(expirationDate).signWith(Keys.hmacShaKeyFor(secret.toByteArray()), SignatureAlgorithm.HS512).compact()
    }


}