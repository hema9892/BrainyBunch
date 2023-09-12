package com.ULB.service.service;

import com.ULB.service.dto.UserDTO;
import com.ULB.service.entity.User;
import com.ULB.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User RegisterUser(UserDTO userDTO) {
        if (userDTO.getPassword().length() < 8) {
            throw new IllegalArgumentException("password must be atleast 8 characters");
        }
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Email already in use");
        }

        User userobj = new User();
        userobj.setUsername(userDTO.getUsername());
        userobj.setEmail(userDTO.getEmail());
        userobj.setPassword(userDTO.getPassword());

       return userRepository.save(userobj);

    }
}
