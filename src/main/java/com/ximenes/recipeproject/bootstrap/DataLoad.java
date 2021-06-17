package com.ximenes.recipeproject.bootstrap;

import com.ximenes.recipeproject.domain.*;
import com.ximenes.recipeproject.enums.Difficulty;
import com.ximenes.recipeproject.repositories.CategoryRepository;
import com.ximenes.recipeproject.repositories.UnitOfMeasureRepository;
import com.ximenes.recipeproject.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Berkson Ximenes
 * Date: 15/06/2021
 * Time: 21:32
 */
@Component
public class DataLoad implements ApplicationRunner {

    private final CategoryRepository categoryRepository;
    private final RecipeService recipeService;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public DataLoad(CategoryRepository categoryRepository, RecipeService recipeService,
                    UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeService = recipeService;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        loadGuacaRecipe();
    }

    private void loadGuacaRecipe() throws IOException {
        Recipe recipe = new Recipe();
        Notes note = new Notes();
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        // Ingredients
        Ingredient avocado = new Ingredient();
        Ingredient salt = new Ingredient();
        Ingredient lime = new Ingredient();
        Ingredient onion = new Ingredient();
        Ingredient chili = new Ingredient();
        Ingredient cilantro = new Ingredient();
        Ingredient pepper = new Ingredient();
        Ingredient tomato = new Ingredient();
        Ingredient garnish = new Ingredient();
        Ingredient tortilla = new Ingredient();

        avocado.setAmount(new BigDecimal(2));
        if (avocado.getAmount().intValue() == 1) {
            avocado.setDescription("Ripe Avocado");
        }
        if (avocado.getAmount().intValue() > 1) {
            avocado.setDescription("Ripe Avocados");
        }
        recipe.addIngredient(avocado);
        avocado.setRecipe(recipe);
        eachUomOptional.ifPresent(avocado::setUom);

        salt.setAmount(new BigDecimal("0.25"));
        salt.setDescription("Salt");
        teaspoonUomOptional.ifPresent(salt::setUom);
        recipe.addIngredient(salt);
        salt.setRecipe(recipe);

        lime.setDescription("Fresh Lime");
        lime.setAmount(new BigDecimal(1));
        tablespoonUomOptional.ifPresent(lime::setUom);
        recipe.getIngredients().add(lime);
        lime.setRecipe(recipe);

        onion.setDescription("Minced red onion");
        onion.setAmount(new BigDecimal(4));
        tablespoonUomOptional.ifPresent(onion::setUom);
        recipe.getIngredients().add(onion);
        onion.setRecipe(recipe);

        chili.setDescription("Serrano (or Jalapeño) chilis, stems and seeds removed, minced");
        chili.setAmount(new BigDecimal(2));
        eachUomOptional.ifPresent(chili::setUom);
        recipe.addIngredient(chili);
        chili.setRecipe(recipe);

        cilantro.setDescription("Cilantro (leaves and tender stems), finely chopped");
        cilantro.setAmount(new BigDecimal(2));
        tablespoonUomOptional.ifPresent(cilantro::setUom);
        recipe.addIngredient(cilantro);
        cilantro.setRecipe(recipe);

        pepper.setDescription("freshly ground black pepper");
        pepper.setAmount(new BigDecimal(2));
        pinchUomOptional.ifPresent(pepper::setUom);
        recipe.addIngredient(pepper);
        pepper.setRecipe(recipe);

        tomato.setDescription("ripe tomato, chopped (optional)");
        tomato.setAmount(new BigDecimal("0.5"));
        recipe.getIngredients().add(tomato);
        tomato.setRecipe(recipe);
        eachUomOptional.ifPresent(tomato::setUom);

        garnish.setDescription("Red radish or jicama slices for garnish (optional)");
        garnish.setAmount(new BigDecimal(4));
        eachUomOptional.ifPresent(garnish::setUom);
        recipe.addIngredient(garnish);
        garnish.setRecipe(recipe);

        tortilla.setDescription("Tortilla chips, to serve");
        tortilla.setAmount(new BigDecimal(10));
        eachUomOptional.ifPresent(tortilla::setUom);
        recipe.addIngredient(tortilla);
        tortilla.setRecipe(recipe);

        recipe.setSource("How to Make the Best Guacamole");
        recipe.setDescription("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, " +
                "onions, chilis, cilantro, and some chopped tomato. Serve it as a dip at your next party or " +
                "spoon it on top of tacos for an easy dinner upgrade");
        recipe.setPrepTime(10);
        recipe.setCookTime(0);
        recipe.setServings(4);
        note.setRecipeNotes("Note 1: Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, " +
                "wash your hands thoroughly after handling, and do not touch your eyes or the area near " +
                "your eyes for several hours afterwards." +
                "Note 2: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.");
        recipe.setNotes(note);
        mexicanCategoryOptional.ifPresent(category -> recipe.getCategories().add(category));
        recipe.setDifficult(Difficulty.MODERATE);
        recipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipe.setDirections("Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the " +
                "flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
                "Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "Add remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance " +
                "to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their " +
                "spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
                "Start with this recipe and adjust to your taste." +
                "Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down " +
                "to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought " +
                "tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.");
        // setando a imagem
        byte[] bytes = new ClassPathResource("static/images/guaca.jpg").getInputStream().readAllBytes();
        Byte[] imageBytes = new Byte[bytes.length];
        Arrays.setAll(imageBytes, n -> bytes[n]);
        recipe.setImage(imageBytes);
        System.out.println("Salvando a receita");
        recipeService.save(recipe);
    }
}
