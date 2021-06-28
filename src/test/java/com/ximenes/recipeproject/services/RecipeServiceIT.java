package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.commands.RecipeCommand;
import com.ximenes.recipeproject.converters.RecipeCommandToRecipe;
import com.ximenes.recipeproject.converters.RecipeToRecipeCommand;
import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Berkson Ximenes
 * Date: 28/06/2021
 * Time: 11:34
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeServiceIT {
    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Test
    @Transactional
    public void testSaveOfDescription() {
        //given
        Iterable<Recipe> recipes = recipeService.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertNotNull(savedRecipeCommand);
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}
