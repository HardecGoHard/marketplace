package com.marketplace.marketplace.service;

import com.marketplace.marketplace.entity.Tag;
import com.marketplace.marketplace.exception.ItemNotFoundException;
import com.marketplace.marketplace.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public boolean existsById(Long id) {
        return tagRepository.existsById(id);
    }

    public Tag getById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("User by id= %d not found", id)));
    }

    public Tag getByUuid(String uuid) {
        return tagRepository.findByUuid(uuid)
                .orElseThrow(() -> new ItemNotFoundException(String.format("User by uuid= %s not found", uuid)));
    }
}
