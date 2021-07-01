package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.commands.UnitOfMeasureCommand;
import com.ximenes.recipeproject.domain.UnitOfMeasure;

import java.util.Set;

/**
 * Created by Berkson Ximenes
 * Date: 30/06/2021
 * Time: 22:27
 */
public interface UnitOfMeasureService extends CrudService<UnitOfMeasure, Long> {

    UnitOfMeasureCommand findCommandById(Long id);

    Set<UnitOfMeasureCommand> listAllUoms();
}
