package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
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
        // verify if findAll method of recipeRepository is executed 2 times.
        verify(recipeRepository, times(1)).findAll();
    }
}