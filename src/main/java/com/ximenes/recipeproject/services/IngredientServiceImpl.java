package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.commands.IngredientCommand;
import com.ximenes.recipeproject.converters.IngredientCommandToIngredient;
import com.ximenes.recipeproject.converters.IngredientToIngredientCommand;
import com.ximenes.recipeproject.domain.Ingredient;
import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.repositories.RecipeRepository;
import com.ximenes.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
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
    private final IngredientCommandToIngredient converterToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand converterToIngredientCommand,
                                 IngredientCommandToIngredient converterToIngredient,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.converterToIngredientCommand = converterToIngredientCommand;
        this.converterToIngredient = converterToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
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

        if (ingredientCommand.isEmpty()) {
            //todo impl error handling
            log.error("Ingredient id not found" + ingredientId);
        }

        return ingredientCommand.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (recipeOptional.isEmpty()) {
            //implement error handling
            log.error("Recipe id not found");
            return new IngredientCommand();
        }

        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                .stream().filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();
        if (ingredientOptional.isPresent()) {
            Ingredient ingredient = ingredientOptional.get();
            ingredient.setDescription(command.getDescription());
            ingredient.setUom(unitOfMeasureRepository
                    .findById(command.getUom().getId()).orElseThrow(() -> new RuntimeException("UOM NOT FOND")));
            ingredient.setAmount(command.getAmount());
        } else {
            recipe.addIngredient(converterToIngredient.convert(command));
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        if (command.getId() != null) {
            return converterToIngredientCommand.convert(savedRecipe
                    .getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst().get());
        } else {
            return converterToIngredientCommand
                    .convert(savedRecipe.getIngredients()
                            .stream().max(Comparator.comparing(Ingredient::getId)).orElse(null));
        }
    }
}
