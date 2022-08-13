package com.github.forworkismail.springbootkotlinjwt.role

import com.github.forworkismail.springbootkotlinjwt.role.dto.RoleDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(private val roleRepository: RoleRepository): RoleService {
    override fun create(createRoleDto: RoleDto): Role {
        return roleRepository.save(Role(name = createRoleDto.name))
    }

    override fun getById(id: Long): Role? {
        return roleRepository.findByIdOrNull(id)
    }

    override fun getAll(): List<Role> {
        return roleRepository.findAll()
    }

    override fun getByName(name: String): Role? {
        return roleRepository.findByName(name)
    }

    override fun update(id: Long, updateRoleDto: RoleDto): Role? {
        return roleRepository.findByIdOrNull(id)?.let {
            roleRepository.save(it.copy(
                name = updateRoleDto.name
            ))
        }
    }

    override fun delete(id: Long) {
        roleRepository.deleteById(id)
    }
}