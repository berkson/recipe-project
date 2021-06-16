package com.ximenes.recipeproject.controllers;

import com.ximenes.recipeproject.domain.Category;
import com.ximenes.recipeproject.domain.UnitOfMeasure;
import com.ximenes.recipeproject.repositories.CategoryRepository;
import com.ximenes.recipeproject.repositories.UnitOfMeasureRepository;
import com.ximenes.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by Berkson Ximenes
 * Date: 08/06/2021
 * Time: 22:47
 */
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                           RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        categoryOptional.ifPresent(category -> System.out.println("Id da categoria é: " + category.getId()));
        unitOfMeasureOptional.ifPresent(unitOfMeasure -> System.out.println("Id da unidade de medida é: " + unitOfMeasure.getId()));
        categoryRepository.findById(2L).ifPresent(category -> System.out.println("Categoria id: 2 - descrição: " + category.getDescription()));

        model.addAttribute("recipes", recipeService.findAll());

        return "index";
    }
}
