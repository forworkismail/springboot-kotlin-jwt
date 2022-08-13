package com.github.forworkismail.springbootkotlinjwt.role

import org.springframework.stereotype.Service

@Service
interface RoleService {
    fun create(role: Role): Role
}