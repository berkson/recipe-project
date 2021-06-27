package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.CategoryCommand;
import com.ximenes.recipeproject.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by berkson
 * Date: 27/06/2021
 * Time: 17:27
 */
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized // spring don't guarantee thread safety. use this for guarantee
    @Nullable // indicate that return can be null
    @Override
    public CategoryCommand convert(Category category) {
        if (category == null) return null;
        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());
        return categoryCommand;
    }
}
