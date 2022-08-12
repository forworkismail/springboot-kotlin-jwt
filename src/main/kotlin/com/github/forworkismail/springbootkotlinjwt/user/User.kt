package com.github.forworkismail.springbootkotlinjwt.user

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true)
    val username: String = "",

    @Column
    @JsonIgnore
    var password: String = "",

    @Column(name = "created_at")
    var createdAt: Date = Date()
) {
    fun copy(username: String, password: String): User {
        return User(username = username, password = password)
    }
}
