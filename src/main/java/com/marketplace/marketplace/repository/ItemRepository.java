package com.marketplace.marketplace.repository;

import com.marketplace.marketplace.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByUuid(String uuid);

    Boolean existsByUuid(String uuid);
}
