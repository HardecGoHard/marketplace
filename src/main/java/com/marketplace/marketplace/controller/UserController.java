package com.marketplace.marketplace.controller;

import com.marketplace.marketplace.assembler.UserDtoAssembler;
import com.marketplace.marketplace.dto.UserDto;
import com.marketplace.marketplace.dto.from.EditProfileDto;
import com.marketplace.marketplace.security.UserPrincipal;
import com.marketplace.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserDtoAssembler userDtoAssembler;
    private final UserService userService;

    @Autowired
    public UserController(
            UserDtoAssembler userDtoAssembler,
            UserService userService
    ) {
        this.userDtoAssembler = userDtoAssembler;
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public UserDto getUser(@PathVariable("userId") Long userId) {
        return userDtoAssembler.toModel(userService.findById(userId));
    }

    @PostMapping("{userId}/avatar")
    public void saveAvatar(
            @RequestParam("avatar") MultipartFile avatar,
            @AuthenticationPrincipal UserPrincipal authUser
    ) {
        userService.uploadAvatar(avatar, authUser);
    }

    @PutMapping("profile")
    public UserDto updateProfile(
            @ModelAttribute EditProfileDto profileDto,
            @AuthenticationPrincipal UserPrincipal authUser
    ) {
        return userDtoAssembler.toModel(userService.updateProfile(profileDto, authUser));
    }
}
