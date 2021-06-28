package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.RecipeCommand;
import com.ximenes.recipeproject.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by berkson
 * Date: 27/06/2021
 * Time: 21:13
 */
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter,
                                 NotesToNotesCommand notesConverter,
                                 CategoryToCategoryCommand categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }


    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) return null;
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipeCommand.getId());
        recipeCommand.setTitle(recipeCommand.getTitle());
        recipeCommand.setDescription(recipeCommand.getDescription());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setDifficulty(recipe.getDifficult());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setUrl(recipeCommand.getUrl());
        recipeCommand.setNotes(notesConverter.convert(recipe.getNotes()));
        recipeCommand.setSource(recipe.getSource());

        if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
            recipe.getCategories()
                    .forEach(category -> recipeCommand.getCategories()
                            .add(categoryConverter.convert(category)));
        }

        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
            recipe.getIngredients()
                    .forEach(ingredient -> recipeCommand.getIngredients()
                            .add(ingredientConverter.convert(ingredient)));
        }

        return recipeCommand;
    }
}
