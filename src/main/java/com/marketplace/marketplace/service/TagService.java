package com.marketplace.marketplace.service;

import com.marketplace.marketplace.entity.Tag;
import com.marketplace.marketplace.exception.entity.TagNotFoundException;
import com.marketplace.marketplace.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TagService extends  AbstractService<Tag, Long>{
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        super(tagRepository);
        this.tagRepository = tagRepository;
    }


    public Tag getByUuid(String uuid) {
        return tagRepository.findByUuid(uuid)
                .orElseThrow(() -> new TagNotFoundException(String.format("Tag by uuid= %s not found", uuid)));
    }

    public Tag getByName(String name) {
        return tagRepository.findByName(name)
                .orElseThrow(() -> new TagNotFoundException(String.format("Tag by name = %s not found", name)));
    }

    public Tag buildPersistTagEntityByName(String name) {
        Optional<Tag> tagEntity = tagRepository.findByName(name);
        if (tagEntity.isPresent()) {
            return tagEntity.get();
        } else {
            Tag newTag = new Tag();
            newTag.setName(name);
            tagRepository.save(newTag);
            return newTag;
        }
    }

    public Set<Tag> getPersistTagsByNames(Set<String> tagStrings) {
        Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tagStrings) {
            tagSet.add(buildPersistTagEntityByName(tagName));
        }
        return tagSet;
    }
}
