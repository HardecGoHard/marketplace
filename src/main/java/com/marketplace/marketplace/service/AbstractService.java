package com.marketplace.marketplace.service;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public abstract class AbstractService<E, I> implements CrudService<E, I> {

    private final JpaRepository<E, I> jpaRepository;

    protected AbstractService(JpaRepository<E, I> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void delete(E entity) {
        jpaRepository.delete(entity);
    }

    @Override
    public void deleteById(I entityId) {
        jpaRepository.deleteById(entityId);
    }

    @Override
    public E findById(I entityId) {
        return jpaRepository.findById(entityId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public E save(E entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public List<E> getAll() {
        return jpaRepository.findAll();
    }

    public boolean existsById(I entityId){
        return jpaRepository.existsById(entityId);
    }
}
