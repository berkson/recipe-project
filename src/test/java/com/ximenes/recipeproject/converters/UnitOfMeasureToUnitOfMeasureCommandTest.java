package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.UnitOfMeasureCommand;
import com.ximenes.recipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 28/06/2021
 * Time: 10:28
 */
class UnitOfMeasureToUnitOfMeasureCommandTest {

    UnitOfMeasureToUnitOfMeasureCommand converter;
    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = 1L;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testForNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testForEmpty() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(LONG_VALUE);
        uom.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand command = converter.convert(uom);

        //then
        assertNotNull(command);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());

    }
}