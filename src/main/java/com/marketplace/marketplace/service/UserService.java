package com.marketplace.marketplace.service;

import com.marketplace.marketplace.dto.from.EditProfileDto;
import com.marketplace.marketplace.entity.Role;
import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.exception.IncorrectPasswordException;
import com.marketplace.marketplace.exception.InvalidRefreshCodeException;
import com.marketplace.marketplace.exception.entity.UserAlreadyExistException;
import com.marketplace.marketplace.exception.entity.UserNotFoundException;
import com.marketplace.marketplace.dto.LoginDto;
import com.marketplace.marketplace.dto.RefreshTokenDto;
import com.marketplace.marketplace.dto.RegistrationModel;
import com.marketplace.marketplace.repository.UserRepository;
import com.marketplace.marketplace.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserService extends AbstractService<User, Long> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ImageService imageService;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ImageService imageService
    ) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
    }


    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("User by username= %s not found", username))
                );
    }

    public User getUserByLoginModel(LoginDto loginDto) {
        User user = getByUsername(loginDto.getUsername());
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new IncorrectPasswordException(
                    String.format(
                            "LoginModel with username= %s password not matches",
                            loginDto.getUsername()
                    )
            );
        }
    }
    public User registrateUser(RegistrationModel registrationModel) {
        //TODO перенести в валидацию регистрационной модели
        if (userRepository.existsByEmail(registrationModel.getEmail())
                || userRepository.existsByUsername(registrationModel.getUsername()))
            throw new UserAlreadyExistException("User with this parameters already exist");

        User newUser = new User();
        newUser
                .setEmail(registrationModel.getEmail())
                .setPassword(passwordEncoder.encode(registrationModel.getPassword()))
                .setUsername(registrationModel.getUsername())
                .setRole(Role.USER);

        return save(newUser);
    }
    public User getUserByRefreshTokenModel(RefreshTokenDto refreshTokenDto) {
        User user = getByUsername(refreshTokenDto.getUsername());
        if (user.getRefreshCode().equals(refreshTokenDto.getRefreshCode())) {
            updateRefreshCode(user);
            return save(user);
        } else {
            throw new InvalidRefreshCodeException(
                    String.format(
                            "RefreshModel with username= %s refresh cod not matches",
                            refreshTokenDto.getUsername()
                    )
            );
        }
    }

    private void updateRefreshCode(User user) {
        user.setRefreshCode(UUID.randomUUID().toString());
    }


    public User updateProfile(EditProfileDto profileDto, UserPrincipal authUser) {
        User user = findById(authUser.getId());

        if (userRepository.existsByEmail(profileDto.getEmail()) && !user.getEmail().equals(profileDto.getEmail()))
            throw new UserAlreadyExistException("email already exist");

        user
                .setName(profileDto.getName())
                .setSurname(profileDto.getSurname())
                .setEmail(profileDto.getEmail());

        return save(user);
    }

    public void uploadAvatar(MultipartFile avatar, UserPrincipal authUser) {
        User user = findById(authUser.getId());
        if (Objects.nonNull(user.getAvatar()) && !avatar.isEmpty()) {
            imageService.deleteFileImage(user.getAvatar());
        }
        user.setAvatar(imageService.saveImage(avatar));

        save(user);
    }
}
