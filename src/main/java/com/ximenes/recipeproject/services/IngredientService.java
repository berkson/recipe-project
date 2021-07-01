package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.commands.IngredientCommand;

/**
 * Created by Berkson Ximenes
 * Date: 30/06/2021
 * Time: 20:46
 */
public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
