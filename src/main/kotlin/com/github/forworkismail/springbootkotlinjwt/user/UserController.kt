package com.github.forworkismail.springbootkotlinjwt.user

import com.github.forworkismail.springbootkotlinjwt.user.dto.CreateUserDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAll(): List<User>  {
        return userService.getAll()
    }

    @PostMapping
    fun create(@RequestBody createUserDto: CreateUserDto): ResponseEntity<Any> {
        if (createUserDto.username.isNotBlank() && userService.getByUsername(createUserDto.username.lowercase()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(mapOf("message" to "Error creating user: username already exists"))
        }
        return ResponseEntity.ok(userService.create(createUserDto))
    }
}