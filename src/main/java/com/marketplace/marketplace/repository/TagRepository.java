package com.marketplace.marketplace.repository;

import com.marketplace.marketplace.entity.Item;
import com.marketplace.marketplace.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);

    Page<Tag> findAll(Pageable pageable);

}
