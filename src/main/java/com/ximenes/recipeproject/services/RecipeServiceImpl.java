package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.commands.RecipeCommand;
import com.ximenes.recipeproject.converters.RecipeCommandToRecipe;
import com.ximenes.recipeproject.converters.RecipeToRecipeCommand;
import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Berkson Ximenes
 * Date: 15/06/2021
 * Time: 23:10
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe converterToRecipe;
    private final RecipeToRecipeCommand converterToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe converterToRecipe,
                             RecipeToRecipeCommand converterToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.converterToRecipe = converterToRecipe;
        this.converterToRecipeCommand = converterToRecipeCommand;
    }

    @Override
    public Recipe save(Recipe object) {
        return recipeRepository.save(object);
    }

    @Override
    public Iterable<Recipe> findAll() {
        log.debug("I'm in the service!");

        return recipeRepository.findAll();
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }


    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe unwrappedRecipe = converterToRecipe.convert(command);
        Recipe savedRecipe = this.save(unwrappedRecipe);
        log.debug("Saved Recipe ID: " + savedRecipe.getId());
        return converterToRecipeCommand.convert(savedRecipe);
    }
}
