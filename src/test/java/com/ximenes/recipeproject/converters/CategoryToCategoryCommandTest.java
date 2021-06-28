package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.CategoryCommand;
import com.ximenes.recipeproject.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 28/06/2021
 * Time: 07:57
 */
class CategoryToCategoryCommandTest {

    private final String DESCRIPTION = "lime";
    private final Long ALONG = Long.valueOf(1);

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void testForNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testForEmpty() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {
        //given
        Category category = new Category();
        category.setId(ALONG);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand command = converter.convert(category);

        //then
        assertNotNull(command);
        assertEquals(ALONG, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}