package com.github.forworkismail.springbootkotlinjwt.role

import javax.persistence.*

@Entity
@Table(name = "roles")
class Role (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String = "",
) {
    fun copy(name: String) = Role(id, name)
}