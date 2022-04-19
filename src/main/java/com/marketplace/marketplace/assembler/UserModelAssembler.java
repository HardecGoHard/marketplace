package com.marketplace.marketplace.assembler;

import com.marketplace.marketplace.entity.User;
import com.marketplace.marketplace.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler extends BaseModelAssembler<User, UserModel> {
    public UserModelAssembler() {
        super(UserModel.class);
    }

    @Override
    public UserModel toModel(User entity) {

        return this.getEntityWithId(entity)
                         .setEmail(entity.getEmail())
                         .setId(entity.getId())
                         .setSurname(entity.getSurname())
                         .setUsername(entity.getUsername())
                         .setName(entity.getName());

    }
}
