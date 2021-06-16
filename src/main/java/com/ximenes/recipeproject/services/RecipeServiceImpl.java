package com.ximenes.recipeproject.services;

import com.ximenes.recipeproject.domain.Recipe;
import com.ximenes.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;


/**
 * Created by Berkson Ximenes
 * Date: 15/06/2021
 * Time: 23:10
 */
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe save(Recipe object) {
        return recipeRepository.save(object);
    }

    @Override
    public Iterable<Recipe> findAll() {
        return recipeRepository.findAll();
    }


}
