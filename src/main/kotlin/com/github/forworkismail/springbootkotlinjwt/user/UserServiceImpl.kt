package com.github.forworkismail.springbootkotlinjwt.user

import com.github.forworkismail.springbootkotlinjwt.user.dto.CreateUserDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
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
                password = createUserDto.password
        ))
    }

    override fun update(id: Long, user: User): User? {
        return userRepository.findByIdOrNull(id)?.let {
            userRepository.save(it.copy(
                    username = user.username,
                    password = user.password
            ))
        }
    }

    override fun delete(id: Long) {
        userRepository.deleteById(id)
    }
}