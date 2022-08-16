package com.github.forworkismail.springbootkotlinjwt.user

import com.github.forworkismail.springbootkotlinjwt.role.RoleController
import com.github.forworkismail.springbootkotlinjwt.role.RoleRepository
import com.github.forworkismail.springbootkotlinjwt.user.dto.CreateUserDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService,
    UserDetailsService {

    private val logger: Logger = LoggerFactory.getLogger(RoleController::class.java)

    override fun getAll(): List<User> {
        return userRepository.findAll()
    }

    override fun getById(id: Long): User? {
        return userRepository.findByIdOrNull(id)
    }

    override fun getByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    override fun create(createUserDto: CreateUserDto): User {
        return userRepository.save(User(
                username = createUserDto.username,
                password = passwordEncoder.encode(createUserDto.password),
        ))
    }

    override fun update(id: Long, user: User): User? {
        return userRepository.findByIdOrNull(id)?.let {
            userRepository.save(it.copy(
                    username = user.username,
                    password = passwordEncoder.encode(user.password)
            ))
        }
    }

    override fun delete(id: Long) {
        userRepository.deleteById(id)
    }

    override fun addRoleToUser(username: String, roleName: String): User? {
        return userRepository.findByUsername(username)?.let {
            userRepository.save(it.copy(
                    roles = it.roles + roleRepository.findByName(roleName)!!
            ))
        }
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val applicationUser = userRepository.findByUsername(username)
            ?: run {
                logger.error("User $username not found in the database")
                throw UsernameNotFoundException("User not found with username: $username")
            }

        logger.info("User $username found in the database")
        return org.springframework.security.core.userdetails.User(
            applicationUser.username, applicationUser.password,
            applicationUser.roles.map { SimpleGrantedAuthority(it.name)}
        )
    }
}