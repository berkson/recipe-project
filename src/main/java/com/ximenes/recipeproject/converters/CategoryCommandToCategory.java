package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.CategoryCommand;
import com.ximenes.recipeproject.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by berkson
 * Date: 27/06/2021
 * Time: 17:04
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {

        if (categoryCommand == null) return null;
        final Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setDescription(categoryCommand.getDescription());
        return category;
    }


}
