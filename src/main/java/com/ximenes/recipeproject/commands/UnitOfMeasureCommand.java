package com.ximenes.recipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by berkson
 * Date: 27/06/2021
 * Time: 17:02
 */
@Setter
@Getter
@NoArgsConstructor
public class UnitOfMeasureCommand {
    private Long id;
    private String description;
}
