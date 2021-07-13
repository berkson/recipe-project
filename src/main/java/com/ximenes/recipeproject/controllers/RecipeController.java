package com.ximenes.recipeproject.controllers;

import com.ximenes.recipeproject.commands.RecipeCommand;
import com.ximenes.recipeproject.exceptions.NotFoundException;
import com.ximenes.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Berkson Ximenes
 * Date: 24/06/2021
 * Time: 23:33
 */
@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final static String RECIPE_RECIPE_FORM_URL = "recipe/recipeform";

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(value = "/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {

        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));

        return "recipe/show";
    }

    @GetMapping(value = "/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping(value = "/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return RECIPE_RECIPE_FORM_URL;
    }

    @PostMapping(value = "/recipe")
    public String saveOrUpdate(@Valid @ModelAttribute RecipeCommand command, BindingResult result) {
        if (result.hasErrors()){
            result.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));

            return RECIPE_RECIPE_FORM_URL;
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return String.format("redirect:/recipe/%d/show", savedCommand.getId());
    }

    //we can do this with get mapping without enable hidden method filter at applications.properties
    @RequestMapping(path = "/recipe/{id}/delete", method = RequestMethod.DELETE)
    public String deleteRecipeById(@PathVariable String id) {
        log.debug("Deleting id: " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/errors/404error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
