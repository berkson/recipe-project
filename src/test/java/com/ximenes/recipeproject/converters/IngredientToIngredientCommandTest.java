package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.IngredientCommand;
import com.ximenes.recipeproject.domain.Ingredient;
import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 28/06/2021
 * Time: 08:04
 */
class IngredientToIngredientCommandTest {
    private static final Recipe RECIPE = new Recipe();
    private final String DESCRIPTION = "description";
    private final String UOM_DESCRIPTION = "UOM description";
    private final Long ING_ID = Long.valueOf(1);
    private final Long UOM_ID = Long.valueOf(2);
    private final BigDecimal AMOUNT = BigDecimal.valueOf(5);

    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testForNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testForEmpty() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        // given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        uom.setDescription(UOM_DESCRIPTION);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ING_ID);
        ingredient.setRecipe(RECIPE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUom(uom);

        // when
        IngredientCommand command = converter.convert(ingredient);

        // then
        assertNotNull(command);
        assertNotNull(command.getUom());
        assertEquals(ING_ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(UOM_ID, command.getUom().getId());
        assertEquals(UOM_DESCRIPTION, command.getUom().getDescription());
    }

    @Test
    void convertWithNullUOM() {
        // given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ING_ID);
        ingredient.setRecipe(RECIPE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        // when
        IngredientCommand command = converter.convert(ingredient);

        // then
        assertNotNull(command);
        assertNull(command.getUom());
        assertEquals(ING_ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
    }
}