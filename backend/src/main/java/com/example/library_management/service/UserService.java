package com.example.library_management.service;

import com.example.library_management.dto.UserDTO;
import com.example.library_management.dto.UpdateUserDTO;
import com.example.library_management.entity.User;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return convertToDTO(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        // Check if username already exists
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO updateUser(Long id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Check if username is being changed and already exists
        if (updateUserDTO.getUsername() != null &&
            !user.getUsername().equals(updateUserDTO.getUsername()) &&
            userRepository.existsByUsername(updateUserDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Check if email is being changed and already exists
        if (updateUserDTO.getEmail() != null &&
            !user.getEmail().equals(updateUserDTO.getEmail()) &&
            userRepository.existsByEmail(updateUserDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Update username if provided
        if (updateUserDTO.getUsername() != null && !updateUserDTO.getUsername().isEmpty()) {
            user.setUsername(updateUserDTO.getUsername());
        }
        
        // Only update password if provided
        if (updateUserDTO.getPassword() != null && !updateUserDTO.getPassword().isEmpty()) {
            user.setPassword(updateUserDTO.getPassword());
        }
        
        // Update email if provided
        if (updateUserDTO.getEmail() != null && !updateUserDTO.getEmail().isEmpty()) {
            user.setEmail(updateUserDTO.getEmail());
        }
        
        if (updateUserDTO.getRole() != null) {
            user.setRole(updateUserDTO.getRole());
        }
        
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                "", // Don't return password in response
                user.getEmail(),
                user.getRole()
        );
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getRole() != null && !userDTO.getRole().isEmpty()) {
            user.setRole(userDTO.getRole());
        }
        // If role is not provided, the entity's default value "ROLE_USER" will be used
        return user;
    }

    private User convertToEntity(UpdateUserDTO updateUserDTO) {
        User user = new User();
        if (updateUserDTO.getUsername() != null) {
            user.setUsername(updateUserDTO.getUsername());
        }
        if (updateUserDTO.getPassword() != null) {
            user.setPassword(updateUserDTO.getPassword());
        }
        if (updateUserDTO.getEmail() != null) {
            user.setEmail(updateUserDTO.getEmail());
        }
        if (updateUserDTO.getRole() != null) {
            user.setRole(updateUserDTO.getRole());
        }
        return user;
    }
}
