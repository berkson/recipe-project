package com.ximenes.recipeproject.controllers;

import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.services.RecipeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by berkson
 * Date: 22/06/2021
 * Time: 06:31
 */
class IndexControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new IndexController(recipeService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getIndexPage() {
        //given
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipes.add(recipe1);
        recipes.add(recipe2);

        when(recipeService.findAll()).thenReturn(recipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.getIndexPage(model);

        //then
        assertEquals("index", viewName);
        verify(recipeService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue(); // quantity of recipes at IndexController recipes Set
        assertEquals(2, setInController.size());
    }
}