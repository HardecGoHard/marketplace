package com.marketplace.marketplace.service;

import java.util.List;

/**
 * @param <E> entity
 * @param <I> entity id type
 */
public interface CrudService <E,I>{

    void delete(E entity);

    void deleteById(I entityId);

    E findById(I entityId);

    E save (E entity);

    List<E> getAll();

}
