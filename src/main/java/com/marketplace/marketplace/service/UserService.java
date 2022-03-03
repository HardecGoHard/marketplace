package com.marketplace.marketplace.service;

import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.exception.IncorrectPasswordException;
import com.marketplace.marketplace.exception.InvalidRefreshCodeException;
import com.marketplace.marketplace.exception.UserNotFoundException;
import com.marketplace.marketplace.model.LoginModel;
import com.marketplace.marketplace.model.RefreshTokenModel;
import com.marketplace.marketplace.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user) {
        usersRepository.save(user);
    }

    public void delete(User user) {
        usersRepository.delete(user);
    }

    public List<User> getAll() {
        return usersRepository.findAll();
    }

    public boolean existsById(Long id) {
        return usersRepository.existsById(id);
    }

    public User getById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User by id= %d not found", id)));
    }

    public User getByUuid(String uuid) {
        return usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException(String.format("User by uuid= %s not found", uuid)));
    }

    public User getByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("User by username= %s not found", username)));
    }

    public User getUserByLoginModel(LoginModel loginModel){
        User user = getByUsername(loginModel.getUsername());
        if (passwordEncoder.matches(loginModel.getPassword(), user.getPassword())){
            return user;
        } else {
            throw new IncorrectPasswordException(String.format("LoginModel with username= %s password not matches", loginModel.getUsername()));
        }
    }

    public User getUserByRefreshTokenModel(RefreshTokenModel refreshTokenModel){
        User user = getByUsername(refreshTokenModel.getUsername());
        if (user.getRefreshCode().equals(refreshTokenModel.getRefreshCode())){
            updateRefreshCode(user);
            usersRepository.save(user);
            return user;
        } else {
            throw new InvalidRefreshCodeException(String.format("RefreshModel with username= %s refresh cod not matches", refreshTokenModel.getUsername()));
        }
    }

    private void updateRefreshCode(User user){
        user.setRefreshCode(UUID.randomUUID().toString());
    }


}
