package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.commands.IngredientCommand;
import com.ximenes.recipeproject.converters.IngredientToIngredientCommand;
import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Berkson Ximenes
 * Date: 30/06/2021
 * Time: 20:47
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand converterToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand converterToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.converterToIngredientCommand = converterToIngredientCommand;
    }

    @Override
    @Transactional
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Recipe unwrappedRecipe = recipeRepository.findById(recipeId).orElseThrow();
        Optional<IngredientCommand> ingredientCommand = unwrappedRecipe.getIngredients()
                .stream()
                .filter(i -> i.getId().equals(ingredientId))
                .map(ingredient -> converterToIngredientCommand.convert(ingredient))
                .findFirst();

        if (ingredientCommand.isEmpty()){
            //todo impl error handling
            log.error("Ingredient id not found" + ingredientId);
        }

        return ingredientCommand.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(recipeOptional.isEmpty()) return new IngredientCommand();

        //todo acabar de implementar esse método

        return null;
    }
}
