package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.commands.RecipeCommand;
import com.ximenes.recipeproject.converters.RecipeCommandToRecipe;
import com.ximenes.recipeproject.converters.RecipeToRecipeCommand;
import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.exceptions.NotFoundException;
import com.ximenes.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe findById(Long id) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isPresent()) {
            return recipeOptional.get();
        } else {
            throw new NotFoundException("Recipe Not Found. For ID value: " + id);
        }
    }


    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe unwrappedRecipe = converterToRecipe.convert(command);
        Recipe savedRecipe = this.save(unwrappedRecipe);
        log.debug("Saved Recipe ID: " + savedRecipe.getId());
        return converterToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional // it's better to use cause the conversion
    public RecipeCommand findCommandById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isPresent()) {
            return converterToRecipeCommand.convert(recipeOptional.get());
        }
        return null;
    }
}
