package com.github.forworkismail.springbootkotlinjwt.role

import com.github.forworkismail.springbootkotlinjwt.role.dto.RoleDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
class RoleController(private val roleService: RoleService) {

    @GetMapping
    fun getAll(): List<Role>  {
        return roleService.getAll()
    }

    @PostMapping
    fun create(@RequestBody createRoleDto: RoleDto): ResponseEntity<Any> {
        if (createRoleDto.name.isNotBlank() && roleService.getByName(createRoleDto.name.lowercase()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mapOf("message" to "Role with name ${createRoleDto.name} already exists"))
        }

        return ResponseEntity.ok(roleService.create(createRoleDto))
    }

    // update role
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody updateRoleDto: RoleDto): ResponseEntity<Any> {
        if (updateRoleDto.name.isNotBlank() && roleService.getByName(updateRoleDto.name.lowercase()) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mapOf("message" to "Role with name ${updateRoleDto.name} does not exists"))
        }
        return ResponseEntity.ok(roleService.update(id, updateRoleDto))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = roleService.delete(id)


}