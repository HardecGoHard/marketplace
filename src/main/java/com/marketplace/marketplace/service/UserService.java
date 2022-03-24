package com.marketplace.marketplace.service;

import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.exception.IncorrectPasswordException;
import com.marketplace.marketplace.exception.InvalidRefreshCodeException;
import com.marketplace.marketplace.exception.entity.UserNotFoundException;
import com.marketplace.marketplace.model.LoginModel;
import com.marketplace.marketplace.model.RefreshTokenModel;
import com.marketplace.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService extends AbstractService<User, Long> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User getByUuid(String uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("User by uuid= %s not found", uuid))
                );
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("User by username= %s not found", username))
                );
    }

    public User getUserByLoginModel(LoginModel loginModel) {
        User user = getByUsername(loginModel.getUsername());
        if (passwordEncoder.matches(loginModel.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new IncorrectPasswordException(
                    String.format(
                            "LoginModel with username= %s password not matches",
                            loginModel.getUsername()
                    )
            );
        }
    }

    public User getUserByRefreshTokenModel(RefreshTokenModel refreshTokenModel) {
        User user = getByUsername(refreshTokenModel.getUsername());
        if (user.getRefreshCode().equals(refreshTokenModel.getRefreshCode())) {
            updateRefreshCode(user);
            return save(user);
        } else {
            throw new InvalidRefreshCodeException(
                    String.format(
                            "RefreshModel with username= %s refresh cod not matches",
                            refreshTokenModel.getUsername()
                    )
            );
        }
    }

    private void updateRefreshCode(User user) {
        user.setRefreshCode(UUID.randomUUID().toString());
    }


}
