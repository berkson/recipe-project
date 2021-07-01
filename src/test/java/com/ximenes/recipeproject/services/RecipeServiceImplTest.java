package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.converters.IngredientToIngredientCommand;
import com.ximenes.recipeproject.converters.RecipeCommandToRecipe;
import com.ximenes.recipeproject.converters.RecipeToRecipeCommand;
import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by berkson
 * Date: 21/06/2021
 * Time: 23:01
 */
class RecipeServiceImplTest {
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    IngredientToIngredientCommand converterToIngredientCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe,
                recipeToRecipeCommand);
    }

    @Test
    void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull(recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void findAll() {
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        // we mus call the repository for data.
        when(recipeRepository.findAll()).thenReturn(recipeData);
        recipeService.findAll().iterator().forEachRemaining(recipes::add);

        assertEquals(1, recipes.size());
        // verify if findAll method of recipeRepository is executed 1 times.
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        //given
        Long idToDelete = 1L;
        recipeService.deleteById(idToDelete);

        //no 'when' cause since method has void return type

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}