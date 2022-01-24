package com.marketplace.marketplace.service;

import com.marketplace.marketplace.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}
