package com.github.forworkismail.springbootkotlinjwt.role

import com.github.forworkismail.springbootkotlinjwt.role.dto.RoleDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(private val roleRepository: RoleRepository): RoleService {

    private val logger: Logger = LoggerFactory.getLogger(RoleController::class.java)

    override fun create(createRoleDto: RoleDto): Role {
        logger.info("Creating new role with name: ${createRoleDto.name}")
        return roleRepository.save(Role(name = createRoleDto.name))
    }

    override fun getById(id: Long): Role? {
        logger.info("Get role by id: $id")
        return roleRepository.findByIdOrNull(id)
    }

    override fun getAll(): List<Role> {
        logger.info("Get all roles")
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