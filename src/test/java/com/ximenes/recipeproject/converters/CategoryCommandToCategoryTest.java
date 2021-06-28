package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.CategoryCommand;
import com.ximenes.recipeproject.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 28/06/2021
 * Time: 07:35
 */
class CategoryCommandToCategoryTest {

    private final String DESCRIPTION = "Brazilian";
    private final Long CAT_ID = Long.valueOf(1);

    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    void testForNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testForEmpty() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        //given
        CategoryCommand command = new CategoryCommand();
        command.setId(CAT_ID);
        command.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(command);

        //then
        assertNotNull(category);
        assertEquals(CAT_ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}