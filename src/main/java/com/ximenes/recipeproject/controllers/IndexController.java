package com.ximenes.recipeproject.controllers;

import com.ximenes.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Berkson Ximenes
 * Date: 08/06/2021
 * Time: 22:47
 */
@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting the index page");
        model.addAttribute("recipes", recipeService.findAll());
        return "index";
    }
}
