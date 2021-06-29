package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.IngredientCommand;
import com.ximenes.recipeproject.commands.UnitOfMeasureCommand;
import com.ximenes.recipeproject.domain.Ingredient;
import com.ximenes.recipeproject.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by berkson
 * Date: 27/06/2021
 * Time: 22:09
 */
class IngredientCommandToIngredientTest {
    private static final Recipe RECIPE = new Recipe();
    private static final BigDecimal AMOUNT = BigDecimal.ONE;
    private static final String DESCRIPTION = "Cheeseburger";
    private static final Long ID_VALUE = Long.valueOf(1);
    private static final Long UOM_ID = Long.valueOf(1);

    IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testForNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testForEmpty() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUom(unitOfMeasureCommand);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    void convertWithNullUom() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }
}