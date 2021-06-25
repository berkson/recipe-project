package com.ximenes.recipeproject.services;


/**
 * Created by Berkson Ximenes
 * Date: 15/06/2021
 * Time: 23:07
 */
public interface CrudService<T, ID> {

    T save(T object);

    Iterable<T> findAll();

    T findById(Long id);
}