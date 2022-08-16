package com.github.forworkismail.springbootkotlinjwt.util.JWT

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter(private val jwtService: JwtService, private val userService: UserDetailsService):
    OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getHeader("Authorization")
        if (jwt != null && jwt.startsWith("Bearer ")) {
            val token = jwt.substring(7)
            val username = jwtService.getUsernameFromToken(token)
            if (username != null && SecurityContextHolder.getContext().authentication == null) {
                val userDetails = userService.loadUserByUsername(username)
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                        .let {
                            it.details = WebAuthenticationDetailsSource().buildDetails(request)
                            SecurityContextHolder.getContext().authentication = it
                        }
                }
            }
        }
        filterChain.doFilter(request, response)
    }

}