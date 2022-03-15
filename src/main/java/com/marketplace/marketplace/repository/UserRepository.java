package com.marketplace.marketplace.repository;

import com.marketplace.marketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUuid(String uuid);

    Optional<User> findByUsername(String uuid);

}
