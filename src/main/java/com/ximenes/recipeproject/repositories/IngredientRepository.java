package com.ximenes.recipeproject.repositories;

import com.ximenes.recipeproject.domain.Ingredient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by berkson
 * Date: 03/07/2021
 * Time: 00:02
 */
@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    //used to remove the ingredient from the table ingredients
    @Modifying
    @Query(value = "DELETE FROM ingredient i WHERE i.recipe_id = ?1 and i.id = ?2",
            nativeQuery = true)
    void deleteByRecipeIdAndId(Long recipeId, Long idToDelete);
}
