package com.github.forworkismail.springbootkotlinjwt.role

import com.github.forworkismail.springbootkotlinjwt.role.dto.RoleDto
import org.springframework.stereotype.Service

@Service
interface RoleService {
    fun create(createRoleDto: RoleDto): Role
    fun getById(id: Long): Role?

    fun getAll(): List<Role>

    fun getByName(name: String): Role?

    fun update(id: Long, updateRoleDto: RoleDto): Role?

    fun delete(id: Long)
}