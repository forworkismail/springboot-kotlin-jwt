package com.github.forworkismail.springbootkotlinjwt.user

import com.github.forworkismail.springbootkotlinjwt.user.dto.CreateUserDto
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun getAll(): List<User>
    fun getById(id: Long): User?
    fun getByUsername(username: String): User?
    fun create(createUserDto: CreateUserDto): User
    fun update(id: Long, User: User): User?
    fun delete(id: Long)
}