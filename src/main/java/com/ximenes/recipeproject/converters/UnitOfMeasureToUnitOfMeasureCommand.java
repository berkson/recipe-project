package com.ximenes.recipeproject.converters;

import com.ximenes.recipeproject.commands.UnitOfMeasureCommand;
import com.ximenes.recipeproject.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by berkson
 * Date: 27/06/2021
 * Time: 18:51
 */
@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null) return null;
        final UnitOfMeasureCommand unit = new UnitOfMeasureCommand();
        unit.setId(unitOfMeasure.getId());
        unit.setDescription(unitOfMeasure.getDescription());
        return unit;
    }
}
