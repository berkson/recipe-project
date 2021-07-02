package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.commands.IngredientCommand;
import com.ximenes.recipeproject.converters.IngredientCommandToIngredient;
import com.ximenes.recipeproject.converters.IngredientToIngredientCommand;
import com.ximenes.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.ximenes.recipeproject.domain.Ingredient;
import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.repositories.RecipeRepository;
import com.ximenes.recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 01/07/2021
 * Time: 20:45
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IngredientServiceIT {

    @Autowired
    IngredientService ingredientService;
    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Autowired
    IngredientToIngredientCommand converterToIngredientCommand;
    @Autowired
    IngredientCommandToIngredient converterToIngredient;
    @Autowired
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    private static final String NEW = "New Description";


    @Test
    @Transactional
    void testSaveIngredientCommandUpdate() {
        //given
        Iterable<Recipe> recipes = recipeService.findAll();
        Recipe testRecipe = recipes.iterator().next();
        assertNotNull(testRecipe.getIngredients());
        Long ingId = testRecipe.getIngredients().stream().findFirst().map(Ingredient::getId).orElse(0L);
        String ingDescription = testRecipe.getIngredients()
                .stream().filter(ingredient -> ingredient.getId().equals(ingId))
                .map(Ingredient::getDescription).collect(Collectors.joining());

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(testRecipe.getId());
        ingredientCommand.setId(ingId);
        ingredientCommand.setDescription(NEW);
        ingredientCommand.setUom(unitOfMeasureToUnitOfMeasureCommand
                .convert(unitOfMeasureRepository.findAll().iterator().next()));
        ingredientCommand.setAmount(BigDecimal.TEN);

        //when
        IngredientCommand command = ingredientService.saveIngredientCommand(ingredientCommand);


        //then
        assertNotNull(command);
        assertNotEquals(ingDescription, testRecipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingId))
                .map(Ingredient::getDescription).collect(Collectors.joining()));
        assertEquals(NEW, command.getDescription());
    }

    @Test
    @Transactional
    void testSaveNewIngredientCommand() {
        //given
        Iterable<Recipe> recipes = recipeService.findAll();
        Recipe testRecipe = recipes.iterator().next();
        int ingQuantity = testRecipe.getIngredients().size();
        assertNotNull(testRecipe.getIngredients());

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(testRecipe.getId());
        ingredientCommand.setDescription(NEW);

        //when
        IngredientCommand command = ingredientService.saveIngredientCommand(ingredientCommand);


        //then
        assertNotNull(command);
        assertEquals(ingQuantity + 1, testRecipe.getIngredients().size());
        assertEquals(NEW, command.getDescription());
    }

    @Test
    @Transactional
    void testDeleteById() {
        //given
        Iterable<Recipe> recipes = recipeService.findAll();
        Recipe testRecipe = recipes.iterator().next();
        Long ingredient_id = testRecipe.getIngredients().stream().iterator().next().getId();
        assertNotNull(testRecipe.getIngredients());

        //when
        ingredientService.deleteById(testRecipe.getId(), ingredient_id);

        //then
        assertFalse(testRecipe.getIngredients()
                .stream().anyMatch(ingredient -> ingredient.getId().equals(ingredient_id)));
    }
}
