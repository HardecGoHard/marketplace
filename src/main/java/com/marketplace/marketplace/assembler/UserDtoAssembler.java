package com.marketplace.marketplace.assembler;

import com.marketplace.marketplace.dto.UserDto;
import com.marketplace.marketplace.entity.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDtoAssembler extends BaseModelAssembler<User, UserDto> {
    public UserDtoAssembler() {
        super(UserDto.class);
    }

    @Override
    public UserDto toModel(User entity) {
        String fullName = (Objects.nonNull(entity.getName())
                && (Objects.nonNull(entity.getSurname())) && !entity.getName().isEmpty()
                && !entity.getSurname().isEmpty()) ?
                entity.getName() + " " + entity.getSurname() : entity.getUsername();

        return this.getEntityWithId(entity)
                .setEmail(entity.getEmail())
                .setId(entity.getId())
                .setSurname(entity.getSurname())
                .setUsername(entity.getUsername())
                .setName(entity.getName())
                .setAvatar(Objects.isNull(entity.getAvatar())? "avatar.jpg" : entity.getAvatar())
                .setFullName(fullName);

    }
}
