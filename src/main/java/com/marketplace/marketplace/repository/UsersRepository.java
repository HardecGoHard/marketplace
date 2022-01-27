package com.marketplace.marketplace.repository;

import com.marketplace.marketplace.entity.Tag;
import com.marketplace.marketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByUuid(String uuid);

}
