package com.marketplace.marketplace.service;

import com.marketplace.marketplace.entity.Tag;
import com.marketplace.marketplace.exception.entity.TagNotFoundException;
import com.marketplace.marketplace.model.TagModel;
import com.marketplace.marketplace.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagService extends  AbstractService<Tag, Long>{
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        super(tagRepository);
        this.tagRepository = tagRepository;
    }

    public void deleteById(Long id){
        Tag tag = findById(id);
        delete(tag);
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

    public Page<Tag> findAllPageble(Pageable pageable){
        return tagRepository.findAll(pageable);
    }

    public Set<Tag> getPersistTagsSetName(Set<String> tagNames) {
        Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tagNames) {
            tagSet.add(buildPersistTagEntityByName(tagName));
        }
        return tagSet;
    }

    public Set<Tag> getPersistTagsByTagModelsSet(Set<TagModel> tagModelSet) {
        Set<Tag> tagSet = tagModelSet
                .stream().map( tag -> buildPersistTagEntityByName(tag.getName()))
                .collect(Collectors.toSet());
        return tagSet;
    }
}
