package com.ximenes.recipeproject.repositories;

import com.ximenes.recipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by berkson
 * Date: 14/06/2021
 * Time: 21:47
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
