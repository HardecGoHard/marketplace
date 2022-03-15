package com.marketplace.marketplace.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractService<E, I> implements CrudService<E, I> {

    private final JpaRepository<E, I> jpaRepository;
    private final Supplier<? extends RuntimeException> exceptionSupplier;

    protected AbstractService(
            JpaRepository<E, I> jpaRepository,
            Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        this.jpaRepository = jpaRepository;
        this.exceptionSupplier = exceptionSupplier;
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
                                .orElseThrow(exceptionSupplier);
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
