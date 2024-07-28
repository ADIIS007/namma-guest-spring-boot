package com.project.namma_guest.service;

import com.project.namma_guest.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    @Autowired
    public UserService(UsersRepository userRepository) {
        this.usersRepository = userRepository;
    }
}
