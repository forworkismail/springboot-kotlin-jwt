package com.github.forworkismail.springbootkotlinjwt.userDetails

import com.github.forworkismail.springbootkotlinjwt.user.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component


@Component
internal class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val applicationUser = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException(username)
        return User(applicationUser.username, applicationUser.password, emptyList())
    }
}