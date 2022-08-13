package com.github.forworkismail.springbootkotlinjwt.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.forworkismail.springbootkotlinjwt.role.Role
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
    val password: String = "",

    @Column(name = "created_at")
    val createdAt: Date = Date(),

    @ManyToMany(fetch = FetchType.EAGER)
    val roles: Set<Role> = HashSet()

) {
    fun copy(username: String, password: String): User {
        return User(username = username, password = password)
    }
}
