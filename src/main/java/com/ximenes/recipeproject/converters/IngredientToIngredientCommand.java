package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.IngredientCommand;
import com.ximenes.recipeproject.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by berkson
 * Date: 27/06/2021
 * Time: 19:02
 */
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) return null;
        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredient.setUom(uomConverter.convert(ingredientCommand.getUnitOfMeasure()));
        return ingredientCommand;
    }
}
