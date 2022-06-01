package com.marketplace.marketplace.repository;

import com.marketplace.marketplace.entity.Comment;
import com.marketplace.marketplace.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByItemId(Long itemId, Pageable pageable);

    List<Comment> findAllByItemId(Long itemId);

    void deleteAllByItem(Item item);

}
