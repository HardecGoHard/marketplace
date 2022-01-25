package com.marketplace.marketplace.service;

import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.exception.ItemNotFoundException;
import com.marketplace.marketplace.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void save(User user){
        usersRepository.save(user);
    }

    public void delete(User user){
        usersRepository.delete(user);
    }

    public List<User> getAll(){
        return usersRepository.findAll();
    }

    public boolean existsById(Long id){
        return usersRepository.existsById(id);
    }

    public User getById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("User by id= %d not found", id)));
    }

    public User getByUuid(String uuid) {
        return usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new ItemNotFoundException(String.format("User by uuid= %s not found", uuid)));
    }
}
