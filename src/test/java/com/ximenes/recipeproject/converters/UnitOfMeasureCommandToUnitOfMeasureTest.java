package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.UnitOfMeasureCommand;
import com.ximenes.recipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by berkson
 * Date: 27/06/2021
 * Time: 21:50
 */
class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = Long.valueOf(1);

    UnitOfMeasureCommandToUnitOfMeasure converter;


    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(new UnitOfMeasureCommand());
    }

    @Test
    void convert() {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(DESCRIPTION, uom.getDescription());
        assertEquals(LONG_VALUE, uom.getId());
    }
}