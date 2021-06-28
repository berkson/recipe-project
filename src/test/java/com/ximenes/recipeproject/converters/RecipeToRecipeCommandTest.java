package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.RecipeCommand;
import com.ximenes.recipeproject.domain.Category;
import com.ximenes.recipeproject.domain.Ingredient;
import com.ximenes.recipeproject.domain.Notes;
import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.enums.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 28/06/2021
 * Time: 10:05
 */
class RecipeToRecipeCommandTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGREDIENT_ID_1 = 3L;
    public static final Long INGREDIENT_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;
    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter =
                new RecipeToRecipeCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                        new NotesToNotesCommand(), new CategoryToCategoryCommand());
    }

    @Test
    void testForNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testForEmpty() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        Category category = new Category();
        category.setId(CAT_ID_1);

        Category category2 = new Category();
        category2.setId(CAT_ID2);

        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID_1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID_2);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertNotNull(command);
        assertNotNull(command.getIngredients());
        assertNotNull(command.getCategories());
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getIngredients().size());
        assertEquals(2, command.getCategories().size());

    }
}