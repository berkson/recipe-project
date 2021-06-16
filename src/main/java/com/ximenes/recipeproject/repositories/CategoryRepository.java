package com.ximenes.recipeproject.repositories;

import com.ximenes.recipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by berkson
 * Date: 14/06/2021
 * Time: 21:49
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
