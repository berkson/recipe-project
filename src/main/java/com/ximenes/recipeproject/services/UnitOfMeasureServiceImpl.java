package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.commands.UnitOfMeasureCommand;
import com.ximenes.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.ximenes.recipeproject.domain.UnitOfMeasure;
import com.ximenes.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Berkson Ximenes
 * Date: 30/06/2021
 * Time: 22:25
 */
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand converterToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand converterToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converterToUnitOfMeasureCommand = converterToUnitOfMeasureCommand;
    }

    @Override
    public UnitOfMeasure save(UnitOfMeasure object) {
        return unitOfMeasureRepository.save(object);
    }

    @Override
    public Iterable<UnitOfMeasure> findAll() {
        return unitOfMeasureRepository.findAll();
    }

    @Override
    public UnitOfMeasure findById(Long id) {
        return unitOfMeasureRepository.findById(id).or(null).get();
    }

    @Override
    public void deleteById(Long id) {
        unitOfMeasureRepository.deleteById(id);
    }

    @Override
    public UnitOfMeasureCommand findCommandById(Long id) {
        UnitOfMeasure unit = this.findById(id);
        return converterToUnitOfMeasureCommand.convert(unit);
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
                .map(converterToUnitOfMeasureCommand::convert).collect(Collectors.toSet());
    }
}
